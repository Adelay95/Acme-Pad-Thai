package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Ingredient;

@Component
@Transactional
public class IngredientToStringConverter implements Converter<Ingredient, String>{
	@Override
	public String convert(Ingredient actor) {
		String result;

		if (actor == null)
			result = null;
		else
			result = String.valueOf(actor.getId());

		return result;
	}

}
