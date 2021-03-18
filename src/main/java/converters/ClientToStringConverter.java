package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Client;

@Component
@Transactional
public class ClientToStringConverter implements Converter<Client, String>{
	@Override
	public String convert(Client campaign) {
		String result;

		if (campaign == null)
			result = null;
		else
			result = String.valueOf(campaign.getId());

		return result;
	}

}
