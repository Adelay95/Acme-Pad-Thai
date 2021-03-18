package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.MonthlyBillRepository;
import domain.MonthlyBill;

@Component
@Transactional
public class StringToMonthlyBillConverter implements Converter<String, MonthlyBill>{

	@Autowired
	MonthlyBillRepository monthlyBillRepository;

	@Override
	public MonthlyBill convert(String text) {
		MonthlyBill result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = monthlyBillRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}


}
