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
import org.stfc.business.SearchDepartmentBusiness;
import org.stfc.dto.Departments;
import org.stfc.message.BaseResponse;
import org.stfc.repository.DepartmentsRepository;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @category Quan ly thong tin cac phong ba
 * @author viett
 *
 */
@RestController
public class DepartmentController {
	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	@Autowired
	DepartmentsRepository repository;
	@Autowired
	FormatMessage formatMessage;

	/**
	 * @category Lay thong tin cac phong ban hien co
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.GET }, value = { "/get_department/" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String onSearch() {
		logger.debug("Methot GET");
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		try {
			SearchDepartmentBusiness bussiness = new SearchDepartmentBusiness(formatMessage, repository);
			response = bussiness.process(gson);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	/**
	 * @category Them moi thong tin cac phong ban
	 * @param httpEntity
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST }, value = { "/department/save" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String saveDepartment(HttpEntity<String> httpEntity) {
		String body = httpEntity.getBody();
		logger.debug("Body request: {}", body);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		String lang = "vi";
		try {
			Departments department = gson.fromJson(body, Departments.class);
			if (department == null) {
				throw new BusinessException(Contants.ERROR_INVALID_FORMAT);
			}
			repository.save(department);
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
	 * @category Xoa thong tin 1 phong ban khoi csdl
	 * @param departmentId
	 * @return
	 */
	@DeleteMapping(value = { "/department/delete/{departmentId}" })
	public String deleteDepartments(@PathVariable Long departmentId) {
		logger.debug("Delete department: {}", departmentId);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		try {
			repository.deleteById(departmentId);
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}
}
