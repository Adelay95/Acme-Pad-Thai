package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EndorserRepository;
import domain.Curricula;
import domain.Endorser;

@Service
@Transactional
public class EndorserService {
	
	@Autowired
	private EndorserRepository endorserRepository;
	@Autowired
	private CurriculaService curriculaService;
	
	public EndorserService(){
		super();
	}
	
	public Endorser create(Curricula curricula){
		Endorser res= new Endorser();
		res.setCurricula(curricula);
		return res;
	}
	
	public Endorser save(Endorser endorser){
		Assert.notNull(endorser);
		Endorser res;
		Curricula cd= endorser.getCurricula();
		res=endorserRepository.save(endorser);
		cd.getEndorser().add(res);
		curriculaService.save(cd);
		return res;
	}
	public void delete(Endorser endorser){
		Assert.notNull(endorser);
		Assert.isTrue(endorser.getId()!=0);
		endorserRepository.delete(endorser);
	}
	
	public  Collection<Endorser> allEndorser(){
		Collection<Endorser> res;
		res=endorserRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public Endorser findOne(int id){
		Endorser res;
		res=endorserRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Collection<Endorser> getEndorser(int curriculaId){
		return endorserRepository.getEndorser(curriculaId);
	}
}
