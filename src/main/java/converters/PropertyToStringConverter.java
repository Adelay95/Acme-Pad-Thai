package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Property;

@Component
@Transactional
public class PropertyToStringConverter implements Converter<Property,String>{


	public String convert(Property folder) {
		String result;
		
		if(folder==null){
			result=null;
		}else{
			result=String.valueOf(folder.getId());
		}
		return result;
	}

}
