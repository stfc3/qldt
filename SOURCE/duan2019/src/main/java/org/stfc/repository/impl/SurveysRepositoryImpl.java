/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stfc.dto.Surveys;
import org.stfc.utils.Comparator;
import org.stfc.utils.StringUtils;

/**
 *
 * @author dongdv
 */
@Repository
public class SurveysRepositoryImpl {

    private static final Logger logger = LoggerFactory.getLogger(SurveysRepositoryImpl.class);
    @Autowired
    EntityManager em;

    public List<Surveys> onSearch(Surveys survey) {
        StringBuilder sql = new StringBuilder("SELECT * FROM surveys u WHERE 1 = 1");
        if (!Comparator.isEqualNull(survey)) {
            if (!Comparator.isEqualNull(survey.getSurveyId())) {
                sql.append(" AND u.survey_id = :surveyId");
            }
            if (!Comparator.isEqualNullOrEmpty(survey.getSurveyTitle())) {
                sql.append(" AND u.survey_title like :surveyTitle escape '/'");
            }
            if (!Comparator.isEqualNullOrEmpty(survey.getSurveyDescription())) {
                sql.append(" AND u.survey_description like :surveyDescription escape '/'");
            }
            if (!Comparator.isEqualNull(survey.getStartTime())) {
                sql.append(" AND u.start_time <= :startTime");
            }
            if (!Comparator.isEqualNull(survey.getEndTime())) {
                sql.append(" AND u.end_time <= :endTime");
            }
            if (!Comparator.isEqualNullOrEmpty(survey.getKeySearch())) {
                sql.append(" AND MATCH (u.survey_title, u.survey_description) AGAINST (:keySearch)");
            }
        }
        Query query =em.createNativeQuery(sql.toString(), Surveys.class);
        
        if (!Comparator.isEqualNull(survey)) {
            if (!Comparator.isEqualNull(survey.getSurveyId())) {
                query.setParameter("surveyId", survey.getSurveyId());
            }
            if (!Comparator.isEqualNullOrEmpty(survey.getSurveyTitle())) {
                query.setParameter("surveyTitle", "%" + StringUtils.escapeCharacter(survey.getSurveyTitle()) + "%");
            }
            if (!Comparator.isEqualNullOrEmpty(survey.getSurveyDescription())) {
                query.setParameter("surveyDescription", "%" + StringUtils.escapeCharacter(survey.getSurveyDescription()) + "%");
            }
            if (!Comparator.isEqualNull(survey.getStartTime())) {
                query.setParameter("startTime", survey.getStartTime());
            }
            if (!Comparator.isEqualNull(survey.getEndTime())) {
                query.setParameter("endTime", survey.getEndTime());
            }
            if (!Comparator.isEqualNullOrEmpty(survey.getKeySearch())) {
                query.setParameter("keySearch", survey.getKeySearch());
            }
        }
        return query.getResultList();
    }
}
