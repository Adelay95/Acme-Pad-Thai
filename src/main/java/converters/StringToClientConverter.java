package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ClientRepository;
import domain.Client;

@Component
@Transactional
public class StringToClientConverter implements Converter<String, Client>{

	@Autowired
	ClientRepository clientRepository;

	@Override
	public Client convert(String text) {
		Client result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = clientRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}


