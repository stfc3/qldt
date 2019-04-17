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
import org.springframework.web.bind.annotation.RequestBody;
import org.stfc.business.BusinessException;
import org.stfc.dto.SurveyResults;
import org.stfc.repository.SurveyResultsRepository;
import org.stfc.utils.Comparator;

/**
 * @author dongdv
 *
 */
@RestController
public class SurveyController {

    private static final Logger logger = LoggerFactory.getLogger(SurveyController.class);
    @Autowired
    SurveyResultsRepository surveyResultsRepository;
    @Autowired
    FormatMessage formatMessage;

    @RequestMapping(value = Constants.PATH.API_SURVEY_IMPORT, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String importSurvey(@RequestBody List<SurveyResults> surveyResults) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(surveyResults));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNull(surveyResults)) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            for (SurveyResults surveyResult : surveyResults) {
                surveyResult.setCreatedDate(new Date());
                surveyResultsRepository.save(surveyResult);
            }
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

}
