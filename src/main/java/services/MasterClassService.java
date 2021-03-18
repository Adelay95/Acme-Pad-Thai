package services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MasterClassRepository;
import domain.Actor;
import domain.Cook;
import domain.LearningMaterial;
import domain.MasterClass;

@Service
@Transactional
public class MasterClassService {
	
	@Autowired
	private MasterClassRepository masterClassRepository;
	@Autowired
	private LearningMaterialService learningMaterialService;
	
	public MasterClassService(){
		super();
	}
	
	public  Collection<MasterClass> allMasterClass(){
		Collection<MasterClass> res;
		res=masterClassRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public MasterClass create(Cook cook){
		MasterClass res;
		Collection<LearningMaterial> learningMaterial=new ArrayList<LearningMaterial>();
		Collection<Actor> a=new HashSet<Actor>();
		res=new MasterClass();
		res.setPromote(false);
		res.setLearningMaterials(learningMaterial);
		res.setCook(cook);
		res.setAlums(a);
		return res;
	}
	
	public MasterClass save(MasterClass masterClass){
		Assert.notNull(masterClass);
		MasterClass res;
		masterClass.setNumPresentation(masterClassRepository.numPresentationsMC(masterClass.getId()));
		masterClass.setNumText(masterClassRepository.numTextMC(masterClass.getId()));
		masterClass.setNumVideos(masterClassRepository.numVideosMC(masterClass.getId()));
		res=masterClassRepository.save(masterClass);
		return res;
	}
	
	public void delete(MasterClass masterClass){
		Assert.notNull(masterClass);
		Assert.isTrue(masterClass.getId()!=0);
		masterClassRepository.delete(masterClass);
	}
	
	public MasterClass findOne(int id){
		MasterClass res;
		res=masterClassRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	//Mirar
	public void addUser(MasterClass masterClass, Actor actor){
		Assert.notNull(actor);
		Assert.notNull(masterClass);
		Assert.isTrue(masterClass.getId()!=0);
		masterClass.getAlums().add(actor);
		save(masterClass);
	}
	
	//QUERY

	public Integer masterClassPromoted() {
		return masterClassRepository.numMasterClassPromoted();
	}

	public Double avgPresentationMasterClass() {
		return masterClassRepository.getAvgPresentationMasterClass();
	}
	public Double avgTextMasterClass() {
		return masterClassRepository.getAvgTextMasterClass();
	}
	public Double avgVideosMasterClass() {
		return masterClassRepository.getAvgVideosMasterClass();
	}

	public void addLearningMaterial(MasterClass masterClass, LearningMaterial text) {
		Assert.notNull(text);
		Assert.notNull(masterClass);
		Assert.isTrue(masterClass.getId()!=0);
		masterClass.getLearningMaterials().add(text);
		MasterClass mc = save(masterClass);
		text.getMasterClass().add(mc);
		learningMaterialService.save(text);
	}

	public void deleteLearningMaterial(MasterClass masterClass, LearningMaterial text) {
		Assert.notNull(text);
		Assert.notNull(masterClass);
		Assert.isTrue(masterClass.getId()!=0);
		Assert.isTrue(masterClass.getLearningMaterials().contains(text));
		masterClass.getLearningMaterials().remove(text);
		MasterClass mc = save(masterClass);
		text.getMasterClass().remove(mc);
		learningMaterialService.save(text);
		
	}
	

}
