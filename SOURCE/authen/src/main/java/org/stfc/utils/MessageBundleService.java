/**
 * 
 */
package org.stfc.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author viettx
 *
 */
@Component
public class MessageBundleService {
	@Autowired
	MessageSource messageSource;

	public String getMessage(String message) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(message, null, locale);
	}

	public String getMessage(String message, Locale locale) {
		return messageSource.getMessage(message, null, locale);
	}

	public String getMessage(String message, String lang) {
		return messageSource.getMessage(message, null, new Locale(lang));
	}
}
