package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.IngredientRepository;
import domain.Ingredient;

@Component
@Transactional
public class StringToIngredientConverter implements Converter<String, Ingredient>{

	@Autowired
	IngredientRepository ingredientRepository;

	@Override
	public Ingredient convert(String text) {
		Ingredient result;
		int id;
		if((text=="")||(text==null)){
			result=null;
		}
		else{
		try {
			id = Integer.valueOf(text);
			result = ingredientRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		}
		return result;
	}

}


