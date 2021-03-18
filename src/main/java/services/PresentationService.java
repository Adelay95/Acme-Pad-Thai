package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PresentationRepository;
import domain.MasterClass;
import domain.Presentation;

@Service
@Transactional
public class PresentationService {
	@Autowired
	private PresentationRepository presentationRepository;
	
	public PresentationService(){
		super();
	}
	
	public Presentation create(){
	
		Presentation res;
		res=new Presentation();
		Collection<MasterClass> ma= new ArrayList<MasterClass>();
		res.setMasterClass(ma);
		return res;
		
	}
	
	public Presentation save(Presentation presentation){
		Assert.notNull(presentation);
		Presentation res;
		res=presentationRepository.save(presentation);
		return res;
	}
	public void delete(Presentation presentation){
		Assert.notNull(presentation);
		Assert.isTrue(presentation.getId()!=0);
		presentationRepository.delete(presentation);
	}
	
	public Presentation findOne(int id){
		Presentation res;
		res=presentationRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	public  Collection<Presentation> allPresentations(){
		Collection<Presentation> res;
		res=presentationRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Presentation> presentationsByMasterClass(int mcId){
		return presentationRepository.presentationsByMasterClass(mcId);
	}
}
