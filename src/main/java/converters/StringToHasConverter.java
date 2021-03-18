package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.HasRepository;
import domain.Has;

@Component
@Transactional
public class StringToHasConverter implements Converter<String, Has>{

	@Autowired
	HasRepository hasRepository;

	public Has convert(String text) {
		Has result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = hasRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
	
}