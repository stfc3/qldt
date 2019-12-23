/**
 * 
 */
package org.stfc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.stfc.entity.HeaderEntity;
import org.stfc.entity.TopCourses;
import org.stfc.entity.TopLecturer;
import org.stfc.message.BaseResponse;
import org.stfc.repository.EvaluationRepository;
import org.stfc.repository.impl.DashboardServices;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;
import org.stfc.utils.Language;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @category Dashboard
 * @author viettx
 *
 */
@RestController
public class DashboardController {
	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	DashboardServices services;
	@Autowired
	FormatMessage formatMessage;

	@Autowired
	EvaluationRepository evaluationRepository;

	@RequestMapping(method = { RequestMethod.GET }, value = { "/dashboard/top/lecturers" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String topLecturer() {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, Language.VI.getValue());
		try {
			List<TopLecturer> listData = services.topLecturer();
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage, Language.VI.getValue());
			response.setData(listData);
			response.setTotal(listData.size());

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	@RequestMapping(method = { RequestMethod.GET }, value = { "/dashboard/top/courses" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String topCourses() {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, Language.VI.getValue());
		try {
			List<TopCourses> listData = services.topCourses();
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage, Language.VI.getValue());
			response.setData(listData);
			response.setTotal(listData.size());

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	@RequestMapping(method = { RequestMethod.GET }, value = { "/dashboard/header" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String getHeader() {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, Language.VI.getValue());
		try {
			Object[] data = services.getHeader();
			HeaderEntity headerEntity = new HeaderEntity(Long.valueOf(String.valueOf(data[0])),
					Long.valueOf(String.valueOf(data[1])), Long.valueOf(String.valueOf(data[2])),
					Long.valueOf(String.valueOf(data[3])));
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage, Language.VI.getValue());
			response.setData(headerEntity);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	@RequestMapping(method = { RequestMethod.GET }, value = { "/dashboard/education_program" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String educationProgram() {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, Language.VI.getValue());
		try {
			Object[] data = services.getHeader();
			HeaderEntity headerEntity = new HeaderEntity(Long.valueOf(String.valueOf(data[0])),
					Long.valueOf(String.valueOf(data[1])), Long.valueOf(String.valueOf(data[2])),
					Long.valueOf(String.valueOf(data[3])));
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage, Language.VI.getValue());
			response.setData(headerEntity);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	@RequestMapping(method = { RequestMethod.GET }, value = { "/dashboard/thematic" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String thematic() {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, Language.VI.getValue());
		try {
			Object[] data = services.getHeader();
			HeaderEntity headerEntity = new HeaderEntity(Long.valueOf(String.valueOf(data[0])),
					Long.valueOf(String.valueOf(data[1])), Long.valueOf(String.valueOf(data[2])),
					Long.valueOf(String.valueOf(data[3])));
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage, Language.VI.getValue());
			response.setData(headerEntity);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	@RequestMapping(method = { RequestMethod.GET }, value = { "/chart/officer" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String chartOfficer() {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, Language.VI.getValue());
		try {
			Object[] data = services.getHeader();
			HeaderEntity headerEntity = new HeaderEntity(Long.valueOf(String.valueOf(data[0])),
					Long.valueOf(String.valueOf(data[1])), Long.valueOf(String.valueOf(data[2])),
					Long.valueOf(String.valueOf(data[3])));
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage, Language.VI.getValue());
			response.setData(headerEntity);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	@RequestMapping(method = { RequestMethod.GET }, value = { "/chart/courser" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String chartCourser() {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, Language.VI.getValue());
		try {
			Object[] data = services.getHeader();
			HeaderEntity headerEntity = new HeaderEntity(Long.valueOf(String.valueOf(data[0])),
					Long.valueOf(String.valueOf(data[1])), Long.valueOf(String.valueOf(data[2])),
					Long.valueOf(String.valueOf(data[3])));
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage, Language.VI.getValue());
			response.setData(headerEntity);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	@RequestMapping(method = { RequestMethod.GET }, value = { "/chart/overall" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String chartOverall() {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, Language.VI.getValue());
		try {
			Object[] data = services.getHeader();
			HeaderEntity headerEntity = new HeaderEntity(Long.valueOf(String.valueOf(data[0])),
					Long.valueOf(String.valueOf(data[1])), Long.valueOf(String.valueOf(data[2])),
					Long.valueOf(String.valueOf(data[3])));
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage, Language.VI.getValue());
			response.setData(headerEntity);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}
	
	
	@RequestMapping(method = { RequestMethod.GET }, value = { "/healthCheck" })
	public String healthCheck() {
		return "Service is running..... Health check OK! Thanks you.";
	}

}
