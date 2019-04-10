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
import org.stfc.dto.RoleObject;
import org.stfc.dto.Roles;
import org.stfc.repository.RoleObjectsRepository;
import org.stfc.repository.RolesRepository;
import org.stfc.repository.impl.RolesRepositoryImpl;
import org.stfc.utils.Comparator;
import org.stfc.utils.IntrospectorUtils;

/**
 * @author dongdv
 *
 */
@RestController
public class RolesController {

    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);
    @Autowired
    RolesRepository rolesRepository;
    @Autowired
    RoleObjectsRepository roleObjectsRepository;
    @Autowired
    RolesRepositoryImpl rolesRepositoryImpl;
    @Autowired
    FormatMessage formatMessage;

    @RequestMapping(value = Constants.PATH.API_ROLES, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveRole(@RequestBody List<Roles> roles) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(roles));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNullOrEmpty(roles)) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            for (Roles role : roles) {
                role.setCreatedDate(new Date());
                role.setUpdatedDate(new Date());
                rolesRepository.save(role);
            }
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }
    @RequestMapping(value = Constants.PATH.API_ROLEOBJECTS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveRoleObject(@RequestBody List<RoleObject> roleObjects) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(roleObjects));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNullOrEmpty(roleObjects)) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            for (RoleObject roleObject : roleObjects) {
                roleObject.setCreatedDate(new Date());
                roleObject.setUpdatedDate(new Date());
                roleObjectsRepository.save(roleObject);
            }
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_ROLES, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateRole(@RequestBody Roles role) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(role));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNull(role) || Comparator.isEqualNull(role.getRoleId())) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            Roles roleCurrent = rolesRepository.getOne(role.getRoleId());
            if (Comparator.isEqualNull(roleCurrent)) {
                throw new BusinessException(Constants.ERROR_INTERNAL);
            } else {
                IntrospectorUtils.cloneEntity(roleCurrent, role);
                roleCurrent.setUpdatedDate(new Date());
                rolesRepository.save(roleCurrent);
                response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            }
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_ROLES + "/{roleId}/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String lockOrUnlockRole(@PathVariable("roleId") Long roleId, @PathVariable("status") Integer status) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNull(roleId) || Comparator.isEqualNull(status)) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            Roles roleCurrent = rolesRepository.getOne(roleId);
            if (Comparator.isEqualNull(roleCurrent)) {
                throw new BusinessException(Constants.ERROR_INTERNAL);
            } else {
                roleCurrent.setStatus(status);
                roleCurrent.setUpdatedDate(new Date());
                rolesRepository.save(roleCurrent);
                response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            }
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }
    @RequestMapping(value = Constants.PATH.API_ROLEOBJECTS + "/{roleObjectId}/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String lockOrUnlockRoleObject(@PathVariable("roleObjectId") Long roleObjectId, @PathVariable("status") Integer status) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNull(roleObjectId) || Comparator.isEqualNull(status)) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            RoleObject roleObjectCurrent = roleObjectsRepository.getOne(roleObjectId);
            if (Comparator.isEqualNull(roleObjectCurrent)) {
                throw new BusinessException(Constants.ERROR_INTERNAL);
            } else {
                roleObjectCurrent.setStatus(status);
                roleObjectCurrent.setUpdatedDate(new Date());
                roleObjectsRepository.save(roleObjectCurrent);
                response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            }
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_ROLES_SEARCH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchRole(@RequestBody Roles role) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(role));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNull(role)) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            List<Roles> listRoles = rolesRepositoryImpl.onSearch(role);
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            if(!Comparator.isEqualNull(listRoles)){
                response.setTotal(listRoles.size());
                response.setRows(listRoles);
            }
            
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }
}
