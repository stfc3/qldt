/**
 * 
 */
package org.stfc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.stfc.business.BusinessException;
import org.stfc.business.SearchPositionsBusiness;
import org.stfc.dto.Positions;
import org.stfc.message.BaseResponse;
import org.stfc.repository.PositionsRepository;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author viett
 *
 */
@RestController
public class PositionsController {
	private static final Logger logger = LoggerFactory.getLogger(PositionsController.class);
	@Autowired
	PositionsRepository repository;
	@Autowired
	FormatMessage formatMessage;

	@RequestMapping(method = { RequestMethod.GET }, value = { "/get_positions/" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String onSearch() {
		logger.debug("Methot GET ");
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		try {
			SearchPositionsBusiness bussiness = new SearchPositionsBusiness(formatMessage, repository);
			response = bussiness.process(gson);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	/**
	 * 
	 * @param httpEntity
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST }, value = { "/positions/save" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String savePositions(HttpEntity<String> httpEntity) {
		String body = httpEntity.getBody();
		logger.debug("Body request: {}", body);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		String lang = "vi";
		try {
			Positions positions = gson.fromJson(body, Positions.class);
			if (positions == null) {
				throw new BusinessException(Contants.ERROR_INVALID_FORMAT);
			}
			repository.save(positions);
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage);
		} catch (BusinessException be) {
			response = BaseResponse.parse(be.getMessage(), formatMessage, lang);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	/**
	 * 
	 * @param positionsId
	 * @return
	 */
	@DeleteMapping(value = { "/positions/delete/{positionsId}" })
	public String deletePositions(@PathVariable Long positionsId) {
		logger.debug("Delete positions: {}", positionsId);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		try {
			repository.deleteById(positionsId);
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}
}
