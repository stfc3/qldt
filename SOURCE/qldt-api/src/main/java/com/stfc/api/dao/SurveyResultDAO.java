/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.api.dao;

import com.stfc.entity.SurveyResults;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dong.dv
 */
@Repository(value = "surveyResultDAO")
public class SurveyResultDAO extends AbstractDAO<Long, SurveyResults> {

}
