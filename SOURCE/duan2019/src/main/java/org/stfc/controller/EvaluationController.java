/**
 * 
 */
package org.stfc.controller;

import java.util.Date;

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
import org.stfc.dto.Evaluations;
import org.stfc.message.BaseResponse;
import org.stfc.repository.EvaluationRepository;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @category Danh gia chat luong lop hoc, khoa hoc
 * @author viettx
 *
 */
@RestController
public class EvaluationController {
	private static final Logger logger = LoggerFactory.getLogger(EvaluationController.class);
	@Autowired
	FormatMessage formatMessage;
	@Autowired
	EvaluationRepository repository;

	/**
	 * Lay thong tin danh gia, nhan xet khoa hoc
	 * 
	 * @param expriencesId
	 * @return
	 */
	@GetMapping("/evaluation/{evaluationId}")
	public String getEvaluation(@PathVariable Long evaluationId) {
		logger.debug("Get evaluation by id {}", evaluationId);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		try {
			if (evaluationId == null) {
				throw new BusinessException(Contants.ERROR_ID_EMPTY);
			}
			Evaluations evaluations = repository.findEvaluationsById(evaluationId);
			if (evaluations == null) {
				throw new BusinessException(Contants.ERROR_DATA_EMPTY);
			}
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage);
			response.setData(evaluations);

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
	 * @category Them thong tin nhan xet, danh gia ve khoa hoc, lop hoc
	 * @param httpEntity
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST }, value = { "/evaluation/save" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String saveEvaluation(HttpEntity<String> httpEntity) {
		String body = httpEntity.getBody();
		logger.debug("Body request: {}", body);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		String lang = "vi";
		try {
			Evaluations evaluations = gson.fromJson(body, Evaluations.class);
			if (evaluations == null) {
				throw new BusinessException(Contants.ERROR_INVALID_FORMAT);
			}
			evaluations.setCreateDate(new Date());
			repository.save(evaluations);
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
	 * @category xoa thong tin danh gia, khoa hoc, lop hoc
	 * @param evaluationId
	 * @return
	 */
	@DeleteMapping(value = { "/evaluation/delete/{evaluationId}" })
	public String deleteExpriences(@PathVariable Long evaluationId) {
		String lang = "vi";
		logger.debug("Delete evaluation: {}", evaluationId);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, lang);
		try {
			if (evaluationId == null) {
				throw new BusinessException(Contants.ERROR_ID_EMPTY);
			}
			repository.deleteById(evaluationId);
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage, lang);
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
