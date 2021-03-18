package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.SocialIdentity;

@Component
@Transactional
public class SocialIdentityToStringConverter implements Converter<SocialIdentity,String>{


	public String convert(SocialIdentity socialIdentity) {
		String result;
		
		if(socialIdentity==null){
			result=null;
		}else{
			result=String.valueOf(socialIdentity.getId());
		}
		return result;
	}


}
