package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Priority;

@Component
@Transactional
public class StringToPriorityConverter implements Converter<String, Priority>{


	@Override
	public Priority convert(String text) {
		Priority result;

		try {
			result = Priority.valueOf(text);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
