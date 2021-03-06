package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Spam;

@Component
@Transactional
public class SpamToStringConverter implements Converter<Spam, String>{
	@Override
	public String convert(Spam actor) {
		String result;

		if (actor == null)
			result = null;
		else
			result = String.valueOf(actor.getId());

		return result;
	}

}
