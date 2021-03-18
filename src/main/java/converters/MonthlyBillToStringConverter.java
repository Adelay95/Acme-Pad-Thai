package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.MonthlyBill;

@Component
@Transactional
public class MonthlyBillToStringConverter implements Converter<MonthlyBill,String>{


	public String convert(MonthlyBill message) {
		String result;
		
		if(message==null){
			result=null;
		}else{
			result=String.valueOf(message.getId());
		}
		return result;
	}

}