package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LearningMaterialRepository;
import domain.LearningMaterial;

@Service
@Transactional
public class LearningMaterialService {
@Autowired
private LearningMaterialRepository learningMaterialRepository;

public LearningMaterial save(LearningMaterial lm){
	Assert.notNull(lm);
	LearningMaterial res;
	res= learningMaterialRepository.save(lm);
	return res;
}

public  Collection<LearningMaterial> allLearningMaterial(){
	Collection<LearningMaterial> res;
	res=learningMaterialRepository.findAll();
	Assert.notNull(res);
	return res;
}

public LearningMaterial findOne(int id) {
		LearningMaterial res;
		res = learningMaterialRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

}
