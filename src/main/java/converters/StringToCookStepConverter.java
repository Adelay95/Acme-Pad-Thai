package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CookStepRepository;

import domain.CookStep;


@Component
@Transactional
public class StringToCookStepConverter implements Converter<String, CookStep>{

	@Autowired
	CookStepRepository cookStepRepository;

	@Override
	public CookStep convert(String text) {
		CookStep result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = cookStepRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}


