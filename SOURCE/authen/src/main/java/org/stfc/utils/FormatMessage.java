package org.stfc.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * 
 * @author viett
 *
 */
@Component
public class FormatMessage {

	@Autowired
	MessageBundleService bundleService;

	/**
	 *
	 * @param program
	 */
	public String getMessage(String code) {
		String description = bundleService.getMessage(code);
		return description;
	}

	public String getMessage(String code, String language) {
		String description = bundleService.getMessage(code, language);
		return description;
	}

}
