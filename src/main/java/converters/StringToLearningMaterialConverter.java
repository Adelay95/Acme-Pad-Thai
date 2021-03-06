package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import repositories.LearningMaterialRepository;

import domain.LearningMaterial;

@Component
@Transactional
public class StringToLearningMaterialConverter implements Converter<String, LearningMaterial>{

	@Autowired
	LearningMaterialRepository learningMaterialRepository;

	@Override
	public LearningMaterial convert(String text) {
		LearningMaterial result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = learningMaterialRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}


