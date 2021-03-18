package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.CookStep;

@Component
@Transactional
public class CookStepToStringConverter implements Converter<CookStep, String>{
	@Override
	public String convert(CookStep cookStep) {
		String result;

		if (cookStep == null)
			result = null;
		else
			result = String.valueOf(cookStep.getId());

		return result;
	}

}
