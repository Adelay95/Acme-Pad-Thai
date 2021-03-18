package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Priority;


@Component
@Transactional
public class PriorityToStringConverter implements Converter<Priority, String>{
	@Override
	public String convert(Priority priority) {
		String result;

		if (priority == null)
			result = null;
		else
			result = priority.toString();

		return result;
	}

}