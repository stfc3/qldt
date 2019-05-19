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
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.stfc.business.BusinessException;
import org.stfc.dto.Objects;
import org.stfc.dto.UserRole;
import org.stfc.dto.Users;
import org.stfc.entity.AuthenRequest;
import org.stfc.entity.AuthenResponse;
import org.stfc.repository.UserRolesRepository;
import org.stfc.repository.UsersRepository;
import org.stfc.repository.impl.UsersRepositoryImpl;
import org.stfc.utils.Comparator;
import org.stfc.utils.IntrospectorUtils;

/**
 * @author dongdv
 *
 */
@RestController
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    UserRolesRepository userRolesRepository;
    @Autowired
    UsersRepositoryImpl usersRepositoryImpl;
    @Autowired
    FormatMessage formatMessage;

    @RequestMapping(value = Constants.PATH.API_LOGIN, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody AuthenRequest authenRequest) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(authenRequest));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNullOrEmpty(authenRequest.getUsername()) || Comparator.isEqualNullOrEmpty(authenRequest.getPassword())) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            AuthenResponse authenResponse = new AuthenResponse();
            Users user = usersRepository.login(authenRequest.getUsername(), authenRequest.getPassword());
            if (Comparator.isEqualNull(user)) {
                throw new BusinessException(Constants.ERROR_LOGIN);
            } else {
                Objects object = usersRepository.getObjectByUser(BigInteger.valueOf(user.getUserId()));
                authenResponse.setUser(user);
                authenResponse.setMenu(object);
            }
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            response.setRows(authenResponse);
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_CHANGE_PASS, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String changePass(@RequestBody AuthenRequest authenRequest) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(authenRequest));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNullOrEmpty(authenRequest.getUsername()) || Comparator.isEqualNullOrEmpty(authenRequest.getPassword())) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            Users user = usersRepository.login(authenRequest.getUsername(), authenRequest.getPassword());
            if (Comparator.isEqualNull(user)) {
                throw new BusinessException(Constants.ERROR_LOGIN);
            } else {
                usersRepository.changePass(user.getUserId(), authenRequest.getPasswordNew());
            }
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_USERS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveUser(@RequestBody List<Users> users) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(users));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNullOrEmpty(users)) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            for (Users user : users) {
                user.setCreatedDate(new Date());
                user.setUpdatedDate(new Date());
                usersRepository.save(user);
            }
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_USERROLES, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveUserRole(@RequestBody List<UserRole> userRoles) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(userRoles));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNullOrEmpty(userRoles)) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            for (UserRole userRole : userRoles) {
                userRole.setCreatedDate(new Date());
                userRole.setUpdatedDate(new Date());
                userRolesRepository.save(userRole);
            }
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_USERS, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateUser(@RequestBody Users user) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(user));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNull(user) || Comparator.isEqualNull(user.getUserId())) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            Users userCurrent = usersRepository.getOne(user.getUserId());
            if (Comparator.isEqualNull(userCurrent)) {
                throw new BusinessException(Constants.ERROR_INTERNAL);
            } else {
                IntrospectorUtils.cloneEntity(userCurrent, user);
                userCurrent.setUpdatedDate(new Date());
                usersRepository.save(userCurrent);
                response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            }
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_USERS + "/{userId}/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String lockOrUnlockUser(@PathVariable("userId") Long userId, @PathVariable("status") Integer status) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNull(userId) || Comparator.isEqualNull(status)) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            Users userCurrent = usersRepository.getOne(userId);
            if (Comparator.isEqualNull(userCurrent)) {
                throw new BusinessException(Constants.ERROR_INTERNAL);
            } else {
                userCurrent.setStatus(status);
                userCurrent.setUpdatedDate(new Date());
                usersRepository.save(userCurrent);
                response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            }
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_USERROLES + "/{userRoleId}/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String lockOrUnlockUserRole(@PathVariable("userRoleId") Long userRoleId, @PathVariable("status") Integer status) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNull(userRoleId) || Comparator.isEqualNull(status)) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            UserRole userRoleCurrent = userRolesRepository.getOne(userRoleId);
            if (Comparator.isEqualNull(userRoleCurrent)) {
                throw new BusinessException(Constants.ERROR_INTERNAL);
            } else {
                userRoleCurrent.setStatus(status);
                userRoleCurrent.setUpdatedDate(new Date());
                userRolesRepository.save(userRoleCurrent);
                response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            }
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_USERS_SEARCH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchUser(@RequestBody(required = false) Users user) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(user));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            List<Users> listUsers = usersRepositoryImpl.onSearch(user);
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            if (!Comparator.isEqualNull(listUsers)) {
                response.setTotal(listUsers.size());
                response.setRows(listUsers);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }
}
