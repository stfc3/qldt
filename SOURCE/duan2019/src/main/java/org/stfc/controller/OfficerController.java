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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.stfc.dto.Officers;
import org.stfc.entity.OfficerResponse;
import org.stfc.repository.impl.OfficersRepositoryImpl;
import org.stfc.utils.Comparator;

/**
 * @author dongdv
 *
 */
@RestController
public class OfficerController {

    private static final Logger logger = LoggerFactory.getLogger(OfficerController.class);
    @Autowired
    OfficersRepositoryImpl officersRepositoryImpl;
    @Autowired
    FormatMessage formatMessage;

    @RequestMapping(value = Constants.PATH.API_OFFICER, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOfficerByUsername(@RequestParam String username) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(username));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            Officers officer = officersRepositoryImpl.findOfficerByUsername(username);
            OfficerResponse officerResponse=new  OfficerResponse();
            if (Comparator.isEqualNull(officer)) {
                response = BaseResponse.parse(Constants.ERROR_DATA_EMPTY, formatMessage, lang);
            }else{
                officerResponse.setOfficer(officer);
                officerResponse.setOfficerCertificates(officersRepositoryImpl.findCertificatesByOfficer(officer.getOfficerId()));
                officerResponse.setCourses(officersRepositoryImpl.findCoursesByOfficer(officer.getOfficerId()));
                response.setData(officerResponse);
                response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            }
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

}
