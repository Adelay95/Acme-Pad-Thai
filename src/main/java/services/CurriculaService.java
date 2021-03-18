package services;



import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculaRepository;
import domain.Curricula;
import domain.Endorser;

@Service
@Transactional
public class CurriculaService {
	
	@Autowired
	private CurriculaRepository curriculaRepository;
	@Autowired
	private EndorserService endorserService;
	
	public CurriculaService(){
		super();
	}
	
	public Curricula create(){
		Curricula res;
		Collection<Endorser> endorser=new HashSet<Endorser>();
		res=new Curricula();
		res.setEndorser(endorser);
		return res;
	}
	
	public Curricula save(Curricula curricula){
		Assert.notNull(curricula);
		Curricula res;
		res=curriculaRepository.save(curricula);
		return res;
	}
	public void delete(Curricula curricula){
		Assert.notNull(curricula);
		Assert.isTrue(curricula.getId()!=0);
		curriculaRepository.delete(curricula);
		borrarEndorsers(curricula);
	}
	

	private void borrarEndorsers(Curricula curricula) {
		Collection<Endorser> endorsers=curricula.getEndorser();
		for(Endorser e:endorsers){
			endorserService.delete(e);
		}
	}

	public Curricula findOne(int id) {
		Curricula res;
		res=curriculaRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

        public  Collection<Curricula> allCurricula(){
		Collection<Curricula> res;
		res=curriculaRepository.findAll();
		Assert.notNull(res);
		return res;
	}

}
