package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Has;

@Component
@Transactional
public class HasToStringConverter implements Converter<Has,String>{


	public String convert(Has folder) {
		String result;
		
		if(folder==null){
			result=null;
		}else{
			result=String.valueOf(folder.getId());
		}
		return result;
	}

}
