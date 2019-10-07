/**
 *
 */
package org.stfc.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.stfc.business.BusinessException;
import org.stfc.dto.Courses;
import org.stfc.dto.Evaluations;
import org.stfc.message.BaseResponse;
import org.stfc.message.CoursesResponse;
import org.stfc.repository.EvaluationRepository;
import org.stfc.repository.impl.CourseServices;
import org.stfc.utils.Comparator;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;
import org.stfc.utils.Language;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
import org.stfc.entity.CoursesView;
import org.stfc.repository.impl.CoursesRepositoryImpl;
import org.stfc.utils.Constants;

/**
 * @category Quan ly lop hoc, khoa hoc
 * @author viettx
 *
 */
@RestController
public class CoursesController {

    private static final Logger logger = LoggerFactory.getLogger(CoursesController.class);

    @Autowired
    CourseServices courseServices;
    @Autowired
    FormatMessage formatMessage;
    @Autowired
    CoursesRepositoryImpl coursesRepositoryImpl;
    @Autowired
    EvaluationRepository evaluationRepository;

    @RequestMapping(method = {RequestMethod.POST}, value = {"/courses/search"}, headers = {
        "Accept=application/json"}, produces = {"text/plain;charset=UTF-8"})
    public String onSearch(@RequestBody(required = false) Courses request) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(request));
        BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, Language.VI.getValue());
        try {
            logger.debug("Request {}", gson.toJson(request));
            List<Courses> listData = courseServices.findAllCourse(request);
            CoursesResponse coursesResponse = new CoursesResponse();
            response = BaseResponse.parse(Contants.SUCCESS, formatMessage, Language.VI.getValue());
            if (!Comparator.isEqualNullOrEmpty(listData)) {
                coursesResponse.setCourses(listData);
                response.setTotal(listData.size());
            }
            response.setData(coursesResponse);

        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    /**
     * Lay thong tin khoa hoc giang vien
     *
     * @param expriencesId
     * @return
     */
    @GetMapping("/courses/{coursesId}")
    public String getCourses(@PathVariable Long coursesId) {
        logger.debug("Get courses by id {}", coursesId);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
        try {
            if (coursesId == null) {
                throw new BusinessException(Contants.ERROR_ID_EMPTY);
            }
            Courses courses = courseServices.findCoursesById(coursesId);
            if (courses == null) {
                throw new BusinessException(Contants.ERROR_DATA_EMPTY);
            }
            response = BaseResponse.parse(Contants.SUCCESS, formatMessage);
            response.setData(courses);

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
     *
     * @param httpEntity
     * @return
     */
    @PostMapping(path = "/courses/save", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String saveCourses(@RequestBody Courses courses) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(courses));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (courses == null) {
                throw new BusinessException(Contants.ERROR_INVALID_FORMAT);
            }
            if (courses.getCourseId() == null) {
                courses.setCreateDate(new Date());
            } else {
                courses.setModifiedDate(new Date());
            }
            courseServices.save(courses);
            response = BaseResponse.parse(Contants.SUCCESS, formatMessage, lang);
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
     * @param coursesId
     * @return
     */
    @DeleteMapping(value = {"/courses/delete/{coursesId}"})
    public String deleteExpriences(@PathVariable Long coursesId) {
        logger.debug("Delete courses: {}", coursesId);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
        String lang = "vi";
        try {
            /**
             * Khong the xoa khi courser id null, thong bao chung loi 02
             */
            if (coursesId == null) {
                throw new BusinessException(Contants.ERROR_ID_EMPTY);
            }
            courseServices.deleteById(coursesId);
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
     * Get list evaluation by courses
     *
     * @param coursesId
     * @return
     */
    @GetMapping(path = "/evaluation/{coursesId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String getEvaluation(@PathVariable Long coursesId) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
        String lang = "vi";
        logger.debug("Get evaluation by courses {}", coursesId);
        try {
            List<Evaluations> listData = evaluationRepository.findByCourseId(coursesId);
            /**
             * Neu khong co du lieu thong bao loi 01
             */
            if (listData == null || listData.isEmpty()) {
                throw new BusinessException(Contants.ERROR_DATA_EMPTY);
            }
            response = BaseResponse.parse(Contants.SUCCESS, formatMessage);
            response.setTotal(listData.size());
            response.setData(listData);
        } catch (BusinessException be) {
            logger.error(be.getMessage(), be);
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);

        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_COURSE_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String exportSurvey(@RequestParam @DateTimeFormat(pattern = Constants.DATE_FORMAT.YYYY_MM_DD) Date fromDate,
            @RequestParam @DateTimeFormat(pattern = Constants.DATE_FORMAT.YYYY_MM_DD) Date toDate) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            List<CoursesView> listCourses = coursesRepositoryImpl.findAllCourses(fromDate, toDate);
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            if (!Comparator.isEqualNull(listCourses)) {
                response.setTotal(listCourses.size());
                response.setData(listCourses);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

}
