package org.stfc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.stfc.business.BusinessException;
import org.stfc.dto.Experiences;
import org.stfc.message.BaseResponse;
import org.stfc.repository.ExperiencesRepository;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @category Thong tin kinh nghiem giang day cua giang vien
 * @author viett
 *
 */
@RestController
public class ExprienceController {
	private static final Logger logger = LoggerFactory.getLogger(ExprienceController.class);
	@Autowired
	ExperiencesRepository experRepository;
	@Autowired
	FormatMessage formatMessage;

	/**
	 * Lay thong tin kinh nghiem giang vien
	 * 
	 * @param expriencesId
	 * @return
	 */
	@GetMapping("/expriences/{expriencesId}")
	public String getExpriences(@PathVariable Long expriencesId) {
		logger.debug("Get expriences by id {}", expriencesId);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		try {
			if (expriencesId == null) {
				throw new BusinessException(Contants.ERROR_ID_EMPTY);
			}
			Experiences experiences = experRepository.findExperiencesById(expriencesId);
			if (experiences == null) {
				throw new BusinessException(Contants.ERROR_DATA_EMPTY);
			}
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage);
			response.setData(experiences);

		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			response = BaseResponse.parse(e.getMessage(), formatMessage);
			// TODO: handle exception
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// TODO: handle exception
		}
		return gson.toJson(response);
	}

	/**
	 * @category xoa kinh nghiem giang vien
	 * @param expriencesId
	 * @return
	 */
	@DeleteMapping(value = { "/expriences/delete/{expriencesId}" })
	public String deleteExpriences(@PathVariable Long expriencesId) {
		logger.debug("Delete expriences: {}", expriencesId);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		try {
			experRepository.deleteById(expriencesId);
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	/**
	 * @category Them moi thong tin kinh nghiem theo giang vien
	 * @param httpEntity
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST }, value = { "/expriences/save" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String saveExpriences(HttpEntity<String> httpEntity) {
		String body = httpEntity.getBody();
		logger.debug("Body request: {}", body);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		String lang = "vi";
		try {
			Experiences experiences = gson.fromJson(body, Experiences.class);
			if (experiences == null) {
				throw new BusinessException(Contants.ERROR_INVALID_FORMAT);
			}
			experRepository.save(experiences);
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage);
		} catch (BusinessException be) {
			logger.error(be.getMessage(), be);
			response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

}
