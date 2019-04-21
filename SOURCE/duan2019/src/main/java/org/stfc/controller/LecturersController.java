/**
 * 
 */
package org.stfc.controller;

import java.util.List;

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
import org.stfc.business.CourseLecturerBusiness;
import org.stfc.business.SaveLecturerBusiness;
import org.stfc.business.SearchLecturersBussiness;
import org.stfc.cache.CacheInfo;
import org.stfc.dto.Courses;
import org.stfc.dto.Experiences;
import org.stfc.dto.Lecturers;
import org.stfc.message.BaseResponse;
import org.stfc.repository.CoursesRepository;
import org.stfc.repository.EvaluationRepository;
import org.stfc.repository.ExperiencesRepository;
import org.stfc.repository.LecturersRepository;
import org.stfc.repository.impl.LecturersCustomerRepositoryImp;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @category Quan ly thong tin giang vien
 * @author viettx
 * @since 04/2019
 */
@RestController
public class LecturersController {
	private static final Logger logger = LoggerFactory.getLogger(LecturersController.class);
	@Autowired
	LecturersRepository lecturerRepository;
	@Autowired
	FormatMessage formatMessage;
	@Autowired
	LecturersCustomerRepositoryImp imp;
	@Autowired
	ExperiencesRepository experRepository;
	@Autowired
	CoursesRepository coursesRepository;
	@Autowired
	CacheInfo cacheInfo;
	@Autowired
	EvaluationRepository evaluationRepository;

	/**
	 * @category Get all officers
	 * @param httpEntity
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST }, value = { "/lecturer/search" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String onSearch(HttpEntity<String> httpEntity) {
		String body = httpEntity.getBody();
		logger.debug("Body request: {}", body);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		try {
			SearchLecturersBussiness bussiness = new SearchLecturersBussiness();
			bussiness.setFormatMessage(formatMessage);
			bussiness.setRepository(lecturerRepository);
			bussiness.setImp(imp);
			bussiness.setCacheInfo(cacheInfo);
			response = bussiness.process(body, gson);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	/**
	 * @category Lay thong tin 1 giang vien bat ky
	 * @param lecturerId
	 * @return
	 */
	@GetMapping(value = "/lecturer/{lecturerId}", headers = { "Accept=application/json" }, produces = {
			"text/plain;charset=UTF-8" })
	public String getLecturer(@PathVariable Long lecturerId) {
		logger.debug("Get lecturer by {}", lecturerId);
		String lang = "vi";
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		try {
			if (lecturerId == null) {
				throw new BusinessException(Contants.ERROR_ID_EMPTY);
			}
			Lecturers lecturers = lecturerRepository.findLecturersById(lecturerId);
			if (lecturers == null) {
				throw new BusinessException(Contants.ERROR_DATA_EMPTY);
			}
//			LecturersEntity entity = new LecturersEntity();
//			entity.setId(lecturers.getId());
//			entity.setFistName(lecturers.getFistName());
//			entity.setLastName(lecturers.getLastName());
//			entity.setGender(lecturers.getGender());
//			entity.setMobile(lecturers.getMobile());
//			entity.setEmail(lecturers.getEmail());
//			Departments department = cacheInfo.getDepartments().get(lecturers.getDeptId());
//			if (department != null) {
//				entity.setDepartment(department.getName());
//			}
//			Positions positions = cacheInfo.getPositions().get(lecturers.getPosiId());
//			if (positions != null) {
//				entity.setPosition(positions.getPosiName());
//			}
//			entity.setStatus(formatMessage.getStatusName(lecturers.getStatus(), lang));
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage, lang);
			response.setData(lecturers);

		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			response = BaseResponse.parse(e.getMessage(), formatMessage, lang);
			// TODO: handle exception
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// TODO: handle exception
		}
		return gson.toJson(response);
	}

	/**
	 * @category Save Lecturer
	 * @param httpEntity
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST }, value = { "/lecturer/save" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String saveLecturer(HttpEntity<String> httpEntity) {
		String lang = "vi";
		String body = httpEntity.getBody();
		logger.debug("Body request: {}", body);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, lang);
		try {
			SaveLecturerBusiness bussiness = new SaveLecturerBusiness();
			bussiness.setFormatMessage(formatMessage);
			bussiness.setRepository(lecturerRepository);
			response = bussiness.process(body, gson);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	/**
	 * 
	 * @param lecturerId
	 * @return
	 */
	@DeleteMapping(value = { "/lecturer/delete/{lecturerId}" })
	public String deleteLecturer(@PathVariable Long lecturerId) {

		logger.debug("Delete lecturer {}", lecturerId);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		try {
			lecturerRepository.deleteById(lecturerId);
			/**
			 * Xoa giang vien thi xoa luon kinh nghiem cua giang vien do
			 */
			List<Experiences> listExp = experRepository.findByLecturerId(lecturerId);
			if (listExp != null && !listExp.isEmpty()) {
				experRepository.deleteAll(listExp);
			}
			/**
			 * Xoa cac khoa hoc
			 */
			List<Courses> listCour = coursesRepository.findByLecturerId(lecturerId);
			if (listCour != null && !listCour.isEmpty()) {
				for (Courses item : listCour) {
					evaluationRepository.deleteByCourse(item.getCourseId());
				}
				coursesRepository.deleteAll(listCour);
			}

			response = BaseResponse.parse(Contants.SUCCESS, formatMessage);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	/**
	 * @category danh sach kinh nghiem giang day theo giang vien
	 * @param httpEntity
	 * @return
	 */

	@GetMapping(value = "/lecturer/expriences/{lecturerId}", headers = { "Accept=application/json" }, produces = {
			"text/plain;charset=UTF-8" })
	public String getExpriencesOfLecturers(@PathVariable Long lecturerId) {
		String lang = "vi";
		logger.debug("lecturer request: {}", lecturerId);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, lang);
		try {
			List<Experiences> listExperiences = experRepository.findByLecturerId(lecturerId);
			if (listExperiences != null && !listExperiences.isEmpty()) {
				throw new BusinessException(Contants.ERROR_DATA_EMPTY);
			}

			response = BaseResponse.parse(Contants.SUCCESS, formatMessage, lang);
			response.setTotal(listExperiences.size());
			response.setData(listExperiences);
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			response = BaseResponse.parse(e.getMessage(), formatMessage, lang);
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	/**
	 * @category danh sach cac khoa hoc cua giang vien
	 * @param httpEntity
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST }, value = { "/lecturer/course" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String getCourseOfLecturers(HttpEntity<String> httpEntity) {
		String body = httpEntity.getBody();
		logger.debug("Body request: {}", body);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		try {
			CourseLecturerBusiness bussiness = new CourseLecturerBusiness();
			bussiness.setFormatMessage(formatMessage);
			bussiness.setRepository(lecturerRepository);
			bussiness.setCoursesRepository(coursesRepository);
			response = bussiness.process(body, gson);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

}
