package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Folder;

@Component
@Transactional
public class FolderToStringConverter implements Converter<Folder,String>{


	public String convert(Folder folder) {
		String result;
		
		if(folder==null){
			result=null;
		}else{
			result=String.valueOf(folder.getId());
		}
		return result;
	}

}
