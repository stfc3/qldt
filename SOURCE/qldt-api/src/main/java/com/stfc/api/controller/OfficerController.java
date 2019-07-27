/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.api.controller;

import com.stfc.api.service.OfficerService;
import com.stfc.entity.Officers;
import com.stfc.schema.STFCResponse;
import com.stfc.utils.Comparator;
import com.stfc.utils.Constants;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dong.dv
 */
@RestController
public class OfficerController {
    
    private static final Logger logger = LogManager.getLogger(OfficerController.class);
    
    @Autowired
    private OfficerService officerService;

    /**
     * @apiVersion 1.0.0
     * @api {GET} /api/v1/officer get officer by username
     * @apiName get officer by username
     * @apiGroup STFC
     *
     * @apiParam {String} username.
     * @apiSuccess {STFCResponse} STFCResponse.
     *
     */
    @RequestMapping(value = Constants.PATH.API_OFFICER, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public STFCResponse getOfficerByUsername(@RequestParam String username) {
        logger.info("START API getOfficerByUsername");
        long startTime = System.currentTimeMillis();
        STFCResponse response = new STFCResponse();
        try {
            Officers officers = officerService.findOfficerByUsername(username);
            if (Comparator.isEqualNull(officers)) {
                response.setReturnCode(Constants.STATUS.NOT_FOUND);
                response.setReturnCode(Constants.MESSAGE.NOT_FOUND);
            } else {
                response.setResult(officers);
            }
            logger.info("END API getOfficerByUsername in " + (System.currentTimeMillis() - startTime) + " ms");
        } catch (Throwable ex) {
            response.setReturnCode(Constants.STATUS.SERVER_ERROR);
            response.setReturnMessage(ex.getMessage());
            logger.error(ex.getMessage(), ex);
            logger.info("END API getOfficerByUsername in " + (System.currentTimeMillis() - startTime) + " ms");
        }
        
        return response;
    }

    /**
     * @apiVersion 1.0.0
     * @api {GET} /api/v1/officer/search find officer
     * @apiName find officer
     * @apiGroup STFC
     *
     * @apiParam {Officers} officer.
     * @apiSuccess {STFCResponse} STFCResponse.
     *
     */
    @RequestMapping(value = Constants.PATH.API_OFFICER + "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public STFCResponse findOfficers(@RequestBody(required = false) Officers officer) {
        logger.info("START API findOfficers");
        long startTime = System.currentTimeMillis();
        STFCResponse response = new STFCResponse();
        try {
            List<Officers> listOfficers = officerService.findOfficers(officer);
            if (Comparator.isEqualNullOrEmpty(listOfficers)) {
                response.setReturnCode(Constants.STATUS.NOT_FOUND);
                response.setReturnCode(Constants.MESSAGE.NOT_FOUND);
            } else {
                response.setTotal(listOfficers.size());
                response.setResult(listOfficers);
            }
            logger.info("END API findOfficers in " + (System.currentTimeMillis() - startTime) + " ms");
        } catch (Throwable ex) {
            response.setReturnCode(Constants.STATUS.SERVER_ERROR);
            response.setReturnMessage(ex.getMessage());
            logger.error(ex.getMessage(), ex);
            logger.info("END API findOfficers in " + (System.currentTimeMillis() - startTime) + " ms");
        }
        
        return response;
    }
    
}
