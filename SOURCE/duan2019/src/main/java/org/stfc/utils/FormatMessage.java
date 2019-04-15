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

	public String getStatusName(int status, String language) {
		String description = "";
		if (status == 0) {
			description = bundleService.getMessage(Contants.STATUS_00, language);
		} else if (status == 1) {
			description = bundleService.getMessage(Contants.STATUS_01, language);
		}
		return description;
	}

}
