package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import repositories.TasteRepository;

import domain.Taste;

@Component
@Transactional
public class StringToTasteConverter implements Converter<String, Taste>{

	@Autowired
	TasteRepository tasteRepository;

	@Override
	public Taste convert(String text) {
		Taste result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = tasteRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}


