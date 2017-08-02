package com.myorg.infra.messagelocalserviceImpl;

import static com.myorg.infra.util.StringConstants.GENERIC_LOCALE_ERROR;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.myorg.infra.messageservice.MessageLocalService;

@Component
public class MessageLocalServiceImpl implements MessageLocalService {

	@Autowired
	private MessageSource messageSource;

	@Override
	public String getMessage(String id) {
		Locale locale = LocaleContextHolder.getLocale();
		String propertyMessage = messageSource.getMessage(id, null, locale);
		if (propertyMessage == null) {
			propertyMessage = GENERIC_LOCALE_ERROR;
		}
		return propertyMessage;
	}

}
