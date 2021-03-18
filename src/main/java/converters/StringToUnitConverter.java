package converters;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Unit;

@Component
@Transactional
public class StringToUnitConverter implements Converter<String, Unit>{

	@Override
	public Unit convert(String text) {
		Unit result;

		try {
			result = Unit.valueOf(text);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}


