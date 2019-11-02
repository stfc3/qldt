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
import org.stfc.business.SearchDepartmentBusiness;
import org.stfc.dto.Departments;
import org.stfc.message.BaseResponse;
import org.stfc.repository.DepartmentsRepository;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.stfc.entity.DepartmentsView;
import org.stfc.repository.impl.DepartmentsRepositoryImpl;
import org.stfc.utils.Comparator;
import org.stfc.utils.Constants;

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
    DepartmentsRepositoryImpl departmentsRepositoryImpl;
    @Autowired
    FormatMessage formatMessage;

    /**
     * @category Lay thong tin cac phong ban hien co
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET}, value = {"/department"}, headers = {
        "Accept=application/json"}, produces = {"text/plain;charset=UTF-8"})
    public String findAllDepartment() {
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
     * Lay thong tin 1 phong ban
     *
     * @param departmentId
     * @return
     */
    @GetMapping(value = "/department/{departmentId}", headers = {"Accept=application/json"}, produces = {
        "text/plain;charset=UTF-8"})
    public String findOneDepartment(@PathVariable Long departmentId) {
        String lang = "vi";
        logger.debug("Get lecturer by {}", departmentId);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (departmentId == null) {
                throw new BusinessException(Contants.ERROR_ID_EMPTY);
            }
            Departments department = repository.findByDepartmentId(departmentId);
            if (department == null) {
                throw new BusinessException(Contants.ERROR_DATA_EMPTY);
            }
            response = BaseResponse.parse(Contants.SUCCESS, formatMessage);
            response.setData(department);
            response.setTotal(1);
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
     * @category Them moi thong tin cac phong ban
     * @param httpEntity
     * @return
     */
    @RequestMapping(method = {RequestMethod.POST}, value = {"/department/save"}, headers = {
        "Accept=application/json"}, produces = {"text/plain;charset=UTF-8"})
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
            if (department.getId() == null) {
                department.setCreateDate(new Date());
            } else {
                department.setModifiedDate(new Date());
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
    @DeleteMapping(value = {"/department/delete/{departmentId}"})
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

    @RequestMapping(value = Constants.PATH.API_DEPARTMENT_STATISTIC, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String findCourseAll(@RequestParam @DateTimeFormat(pattern = Constants.DATE_FORMAT.YYYY_MM_DD) Date fromDate,
            @RequestParam @DateTimeFormat(pattern = Constants.DATE_FORMAT.YYYY_MM_DD) Date toDate) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            List<DepartmentsView> listDepartmentsView = departmentsRepositoryImpl.findDepartmentStatistic(fromDate, toDate);
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            if (!Comparator.isEqualNull(listDepartmentsView)) {
                response.setTotal(listDepartmentsView.size());
                response.setData(listDepartmentsView);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

}
