package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.EndorserRepository;
import domain.Endorser;

@Component
@Transactional
public class StringToEndorserConverter implements Converter<String, Endorser>{

	@Autowired
	EndorserRepository endorserRepository;

	@Override
	public Endorser convert(String text) {
		Endorser result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = endorserRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
