package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CookRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Cook;
import domain.Folder;
import domain.LearningMaterial;
import domain.MasterClass;
import domain.Message;
import domain.Priority;
import domain.SocialIdentity;

@Service
@Transactional
public class CookService {

	@Autowired
	private CookRepository cookRepository;
	@Autowired
	private MasterClassService masterClassService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private LearningMaterialService learningMaterialService;
	@Autowired
	private MessageService messageService;
	
	public CookService() {
		super();
	}

	public Cook create() {
		Cook res;
		Collection<MasterClass> masterClass = new ArrayList<MasterClass>();
		Collection<Folder> folders = new ArrayList<Folder>();
		Collection<SocialIdentity> socialIdentities = new HashSet<SocialIdentity>();
		Collection<Message> messageSent = new HashSet<Message>();
		Collection<Message> messageReceived = new HashSet<Message>();
		UserAccount userAccount=new UserAccount();
		Authority aut=new Authority();
		aut.setAuthority("COOK");
		Collection<Authority> authorities=new HashSet<Authority>();
		authorities.add(aut);
		userAccount.setAuthorities(authorities);
		
		res = new Cook();
		res.setUserAccount(userAccount);
		res.setSocialIdentities(socialIdentities);
		res.setMessageReceived(messageReceived);
		res.setMessageSent(messageSent);
		res.setMasterClass(masterClass);
		res.setFolders(folders);
		return res;
	}

	public Cook save(Cook cook) {
		Assert.notNull(cook);
		Cook res;
		Integer numPromotes=cookRepository.getNumMCPromoted(cook.getId());
		cook.setNumPromotes(numPromotes);
		res = cookRepository.save(cook);
		if (res.getFolders().isEmpty()) {
			Collection<Folder> folder = actorService.createFolderss(res);
			res.setFolders(folder);
			res = cookRepository.save(res);
		}
		return res;
	}

	public void delete(Cook cook) {
		Assert.notNull(cook);
		Assert.isTrue(cook.getId() != 0);
		for (MasterClass m : cook.getMasterClass()) {
			masterClassService.delete(m);
		}
		cookRepository.delete(cook);
	}

	public Collection<Cook> allCooks() {
		Collection<Cook> res;
		res = cookRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Cook findOne(int id) {
		Cook res;
		res = cookRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Cook findByPrincipal() {
		Cook res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = findByCookAccount(userAccount);
		Assert.notNull(res);
		return res;
	}

	private Cook findByCookAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Cook res;
		res = cookRepository.findByUserAccountId(userAccount.getId());
		return res;

	}

	// Nuestros

	public Collection<MasterClass> mostrarMasterClassCook(Integer cook) {
		Assert.notNull(cook);
		return cookRepository.masterClassCook(cook);
	}

	public MasterClass crearMasterClass(MasterClass res) {
		Cook cook;
		Assert.notNull(res);
		cook = this.findByPrincipal();
		MasterClass saved;
		
		res.setCook(cook);
		saved = masterClassService.save(res);
		cook.getMasterClass().add(saved);
		save(cook);
		
		return saved;
	}

	public void borrarMaterClass(MasterClass masterClass) {
		Assert.notNull(masterClass);
		Cook cook;
		cook = this.findByPrincipal();
		Assert.notNull(cook);

		Collection<Actor> c = masterClass.getAlums();
		for (Actor actores : c) {
			Message message=messageService.create(cook,actores);
			message.setBody("La masterClass con titulo: "+masterClass.getTitle()+" ha sido borrada");
			message.setPriority(Priority.HIGH);
			message.setSubject("Borrado de la masterClass");
			Message saved=messageService.save(message);
			actorService.sendMessage(saved);
		}
		Cook co= masterClass.getCook();
			co.getMasterClass().remove(masterClass);
			save(cook);
		
		
		for(LearningMaterial lm:masterClass.getLearningMaterials()){
			lm.getMasterClass().remove(masterClass);
			learningMaterialService.save(lm);
		}
		masterClassService.delete(masterClass);
	}
	

	//QUERY

	public Integer minMasterClassCook() {
		return cookRepository.getMinMasterClassCook();
	}
	public Integer maxMasterClassCook() {
		return cookRepository.getMaxMasterClassCook();
	}
	public Double avgMasterClassCook() {
		return cookRepository.getAvgMasterClassCook();
	}
	public Double devMasterClassCook() {
		return cookRepository.getDevMasterClassCook();
	}
	

	public Collection<Cook> rankingMasterClassPromeoted() {
		return cookRepository.getRankingMasterClassPromeoted();
	}

	public Double avgCookNumPromoted() {
		return cookRepository.getAvgCookNumPromoted();
	}
	public Double avgCookNumDemoted() {
		return cookRepository.getAvgCookNumDemoted();
	}
}
