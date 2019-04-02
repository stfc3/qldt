/**
 * 
 */
package org.stfc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.stfc.business.UsersBusiness;
import org.stfc.message.BaseResponse;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.stfc.repository.UsersRepository;

/**
 * @author dongdv
 *
 */
@RestController
public class UsersController {
	private static final Logger logger = LoggerFactory.getLogger(UsersController.class);
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	FormatMessage formatMessage;

	@RequestMapping(method = { RequestMethod.POST }, value = { "/get_user/" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String getAllUsers(HttpEntity<String> httpEntity) {
		String body = httpEntity.getBody();
		logger.debug("Body request: {}", body);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		try {
			UsersBusiness bussiness = new UsersBusiness();
			bussiness.setFormatMessage(formatMessage);
			bussiness.setRepository(usersRepository);
			response = bussiness.process(body, gson);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}
}
