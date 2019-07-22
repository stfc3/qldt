/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.api.dao;

import com.stfc.entity.Surveys;
import com.stfc.utils.Comparator;
import java.util.List;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dong.dv
 */
@Repository(value = "surveyDAO")
public class SurveyDAO extends AbstractDAO<Long, Surveys> {

    public List<Surveys> findAllSurveys() {
        Query query = getSession().createNamedQuery("Surveys.findAll");
        List listSurvey = query.list();
        if (!Comparator.isEqualNullOrEmpty(listSurvey)) {
            return listSurvey;
        }
        return null;
    }

}
