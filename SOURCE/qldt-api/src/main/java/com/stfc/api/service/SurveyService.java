/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.api.service;

import com.stfc.api.dao.SurveyDAO;
import com.stfc.entity.Surveys;
import com.stfc.utils.Constants;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dong.dv
 */
@Service
@Transactional(transactionManager = Constants.CONFIG.TRANSACTION)
public class SurveyService {

    @Autowired
    private SurveyDAO surveyDAO;
    
    public List<Surveys> findAllSurveys(){
        return surveyDAO.findAllSurveys();
    }

}
