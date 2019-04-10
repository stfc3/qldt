/**
 *
 */
package org.stfc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.stfc.message.BaseResponse;
import org.stfc.utils.Constants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Date;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.stfc.business.BusinessException;
import org.stfc.dto.Objects;
import org.stfc.repository.ObjectsRepository;
import org.stfc.repository.impl.ObjectsRepositoryImpl;
import org.stfc.utils.Comparator;
import org.stfc.utils.IntrospectorUtils;

/**
 * @author dongdv
 *
 */
@RestController
public class ObjectsController {

    private static final Logger logger = LoggerFactory.getLogger(ObjectsController.class);
    @Autowired
    ObjectsRepository objectsRepository;
    @Autowired
    ObjectsRepositoryImpl objectsRepositoryImpl;
    @Autowired
    FormatMessage formatMessage;

    @RequestMapping(value = Constants.PATH.API_OBJECTS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveObject(@RequestBody List<Objects> objects) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(objects));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNullOrEmpty(objects)) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            for (Objects object : objects) {
                object.setCreatedDate(new Date());
                object.setUpdatedDate(new Date());
                objectsRepository.save(object);
            }
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_OBJECTS, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateObject(@RequestBody Objects object) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(object));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNull(object) || Comparator.isEqualNull(object.getObjectId())) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            Objects objectCurrent = objectsRepository.getOne(object.getObjectId());
            if (Comparator.isEqualNull(objectCurrent)) {
                throw new BusinessException(Constants.ERROR_INTERNAL);
            } else {
                IntrospectorUtils.cloneEntity(objectCurrent, object);
                objectCurrent.setUpdatedDate(new Date());
                objectsRepository.save(objectCurrent);
                response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            }
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_OBJECTS + "/{objectId}/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String lockOrUnlockObject(@PathVariable("objectId") Long objectId, @PathVariable("status") Integer status) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNull(objectId) || Comparator.isEqualNull(status)) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            Objects objectCurrent = objectsRepository.getOne(objectId);
            if (Comparator.isEqualNull(objectCurrent)) {
                throw new BusinessException(Constants.ERROR_INTERNAL);
            } else {
                objectCurrent.setStatus(status);
                objectCurrent.setUpdatedDate(new Date());
                objectsRepository.save(objectCurrent);
                response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            }
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_OBJECTS_SEARCH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchObject(@RequestBody Objects object) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(object));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNull(object)) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            List<Objects> listObjects = objectsRepositoryImpl.onSearch(object);
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            if(!Comparator.isEqualNull(listObjects)){
                response.setTotal(listObjects.size());
                response.setRows(listObjects);
            }
            
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }
}
