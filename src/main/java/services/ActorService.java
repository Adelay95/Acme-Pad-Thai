package services;

import java.util.Collection;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Folder;
import domain.MasterClass;
import domain.Message;
import domain.SocialIdentity;
import domain.Spam;

@Service
@Transactional
public class ActorService {
	
	@Autowired
	private ActorRepository actorRepository;
	@Autowired
  	private FolderService folderService;
	@Autowired
  	private MessageService messageService;
	@Autowired
  	private SpamService spamService;
	@Autowired
	private MasterClassService masterClassService;
	
	
	public Actor save(Actor actor){
		Assert.notNull(actor);
		Actor res;
		res=actorRepository.save(actor);
		return res;
	}
	
	
	public Collection<Folder> createFolderss(Actor actor){
		Assert.notNull(actor);
		Folder folder1=folderService.create(actor);
		Folder folder2=folderService.create(actor);
		Folder folder3=folderService.create(actor);
		Folder folder4=folderService.create(actor);
		folder1.setName("INBOX");
		folder2.setName("OUTBOX");
		folder3.setName("TRASHBOX");
		folder4.setName("SPAMBOX");
		Folder folder1saved=folderService.save(folder1);
		Folder folder2saved=folderService.save(folder2);
		Folder folder3saved=folderService.save(folder3);
		Folder folder4saved=folderService.save(folder4);

		actor.getFolders().add(folder1saved);
		actor.getFolders().add(folder2saved);
		actor.getFolders().add(folder3saved);
		actor.getFolders().add(folder4saved);
		//Actor a=save(actor);
		return actor.getFolders();
	}
	
	public Boolean spam(Message m){
		Boolean res=false;
		for(Spam s:spamService.allSpam()){
			
				if(m.getBody().contains(s.getTerms())|| m.getSubject().contains(s.getTerms())){
					res=true;
					break;
				
			}
			
		}return res;
	}
	public void sendMessage(Message message){
		Assert.notNull(message);
		Boolean spam=spam(message);
		Actor sender= message.getSender();
		Folder folder1= message.getFolder();
		Actor receiver=message.getReceiver();
		Folder folder2 = null;
		Message message2=messageService.create(sender, receiver);
		message2.setBody(message.getBody());
		message2.setMoment(message.getMoment());
		message2.setPriority(message.getPriority());
		message2.setSubject(message.getSubject());
		if(spam){
			folder2=folderService.findSPAMBOX(receiver);
		}else{
			folder2= folderService.findINBOX(receiver);
		}
		message2.setFolder(folder2);
		Message saved1=messageService.save(message);
		Message saved2=messageService.save(message2);
		folder1.getMessages().add(saved1);
		folder2.getMessages().add(saved2);
		sender.getMessageSent().add(saved1);
		receiver.getMessageReceived().add(saved2);
		folderService.save(folder1);
		folderService.save(folder2);
		save(sender);save(receiver);
	}
	
	public Actor actualizaActor(Actor actor, String name, String surname, String emailAdress, String postalAdress,
			String phoneNumber, Collection<SocialIdentity> sI){
		Assert.notNull(actor);
		Assert.notNull(name);
		Assert.notNull(surname);
		Assert.notNull(emailAdress);
		Assert.notNull(sI);
		actor.setName(name);
		actor.setSurname(surname);
		actor.setEmailAdress(emailAdress);
		actor.setPhoneNumber(phoneNumber);
		actor.setPostalAdress(postalAdress);
		actor.setSocialIdentities(sI);
		return save(actor);
		
	}
	
	
	public Folder createFolder(Actor actor, String name){
		Assert.notNull(name);
		Folder folder, res;
		folder=folderService.create(actor);
		folder.setName(name);
		res=folderService.save(folder);
		return res;
	}
	
	public Collection<Folder> showFolders(Actor actor){
		Collection<Folder> res;
		res=actor.getFolders();
		return res;
	}
	public Folder actualizaFolder(Integer idFolder, String nombre){
		Folder modificar;
		modificar=folderService.findOne(idFolder);
		Assert.notNull(modificar);
		modificar.setName(nombre);
		return folderService.save(modificar);
	}
	
	public void deleteFolder(Folder folder){
		Assert.notNull(folder);
		Assert.isTrue(folder.getId()!=0);
		if(!(folder.getName()=="INBOX" || folder.getName()=="OUTBOX" || folder.getName()=="TRASHBOX"
				|| folder.getName()=="SPAMBOX")){
		Actor actor = folder.getActor();
		actor.getFolders().remove(folder);
		save(actor);
		folderService.delete(folder);
		}
	}
	
	public void deleteMessage(Message message){
		Assert.notNull(message);
	    Folder f=message.getFolder();
	    Actor actor1=f.getActor();
	    f.getMessages().remove(message);
	    if(!f.getName().equals("TRASHBOX")){
	    	for(Folder f1:actor1.getFolders()){
	    		if(f1.getName().equals("TRASHBOX")){
	    			f.getMessages().remove(message);
	    			folderService.save(f);
	    			message.setFolder(f1);
	    			Message messageCalite=messageService.save(message);
	    			f1.getMessages().add(messageCalite);
	    			folderService.save(f1);
	    			break;
	    		}
	    	}
	    }else{
	    	f.getMessages().remove(message);
	    	folderService.save(f);
	    	messageService.deleteFinish(message);
	    }
	    
	}
	
	public void moveMessage(Message message, Folder newOne){
		Assert.notNull(message);
		Assert.notNull(newOne);
		Folder f1= message.getFolder();
		f1.getMessages().remove(message);
		folderService.save(f1);
		newOne.getMessages().add(message);
		message.setFolder(newOne);
		messageService.save(message);
		folderService.save(newOne);
	}
	
	public Collection<String> informacionMasterClass(){
		return actorRepository.informacionMasterClass();
	}
	
	public void registerMC(MasterClass mc, Actor ac){
		Assert.notNull(mc);
		Assert.notNull(ac);
		mc.getAlums().add(ac);
		masterClassService.save(mc);
	}
	
	public Actor findByPrincipal() {
		Actor res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = findByUserAccount(userAccount);
		Assert.notNull(res);
		return res;
	}

	private Actor findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Actor res;
		res = actorRepository.findByUserAccountId(userAccount.getId());
		return res;

	}
	
	public Collection<Actor> allActors(){
		Collection<Actor> actors=actorRepository.findAll();
		Assert.notNull(actors);
		return actors;
	}


	public Actor findOne(int actorId) {
		Actor res;
		res = actorRepository.findOne(actorId);
		Assert.notNull(res);
		return res;
	}

}
