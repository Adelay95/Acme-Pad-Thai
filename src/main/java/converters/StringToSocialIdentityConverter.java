package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.SocialIdentityRepository;
import domain.SocialIdentity;


@Component
@Transactional
public class StringToSocialIdentityConverter implements Converter<String, SocialIdentity>{

	@Autowired
	SocialIdentityRepository socialIdentityRepository;

	@Override
	public SocialIdentity convert(String text) {
		SocialIdentity result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = socialIdentityRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}


}