/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.api.controller;

import com.stfc.api.service.SurveyService;
import com.stfc.entity.Surveys;
import com.stfc.schema.STFCResponse;
import com.stfc.utils.Comparator;
import com.stfc.utils.Constants;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dong.dv
 */
@RestController
public class SurveyController {

    private static final Logger logger = LogManager.getLogger(SurveyController.class);

    @Autowired
    private SurveyService surveyService;

    /**
     * @apiVersion 1.0.0
     * @api {GET} /api/v1/test Insert action log
     * @apiName Insert action log
     * @apiGroup Support
     *
     * @apiParam {ActionLog} .
     * @apiSuccess {SHBFResponse} SHBFResponse.
     *
     */
    @RequestMapping(value = "/api/v1/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public STFCResponse test() {
        logger.info("START API test");
        long startTime = System.currentTimeMillis();

        STFCResponse response = new STFCResponse();
        try {
            List<Surveys> listSurveys = surveyService.findAllSurveys();
            if (Comparator.isEqualNullOrEmpty(listSurveys)) {
                response.setReturnCode(Constants.STATUS.NOT_FOUND);
                response.setReturnCode(Constants.MESSAGE.NOT_FOUND);
            } else {
                response.setResult(listSurveys);
            }
            logger.info("END API test in " + (System.currentTimeMillis() - startTime) + " ms");
        } catch (Throwable ex) {
            response.setReturnCode(Constants.STATUS.SERVER_ERROR);
            response.setReturnMessage(ex.getMessage());
            logger.error(ex.getMessage(), ex);
            logger.info("END API insertLog in " + (System.currentTimeMillis() - startTime) + " ms");
        }

        return response;
    }

}
