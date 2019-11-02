/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.repository.impl;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stfc.dto.SurveyResults;
import org.stfc.utils.Comparator;

/**
 *
 * @author dongdv
 */
@Repository
public class SurveyResultsRepositoryImpl {

    private static final Logger logger = LoggerFactory.getLogger(SurveyResultsRepositoryImpl.class);
    @Autowired
    EntityManager em;

    public List<SurveyResults> onSearch(SurveyResults surveyResult) {
        StringBuilder sql = new StringBuilder("SELECT * FROM survey_results u WHERE 1 = 1");
        if (!Comparator.isEqualNull(surveyResult)) {
            if (!Comparator.isEqualNull(surveyResult.getSurveyId())) {
                sql.append(" AND u.survey_id = :surveyId");
            }
            if (!Comparator.isEqualNull(surveyResult.getQuestionId())) {
                sql.append(" AND u.question_id = :questionId");
            }
            if (!Comparator.isEqualNull(surveyResult.getOfficerId())) {
                sql.append(" AND u.officer_id = :officerId");
            }
            if (!Comparator.isEqualNullOrEmpty(surveyResult.getAnswer())) {
                sql.append(" AND u.answer = :answer");
            }
            if (!Comparator.isEqualNull(surveyResult.getLearnFromDate())) {
                sql.append(" AND u.learn_from_date = :learnFromDate");
            }
            if (!Comparator.isEqualNull(surveyResult.getLearnToDate())) {
                sql.append(" AND u.learn_to_date = :learnToDate");
            }
        }
        Query query = em.createNativeQuery(sql.toString(), SurveyResults.class);

        if (!Comparator.isEqualNull(surveyResult)) {
            if (!Comparator.isEqualNull(surveyResult.getSurveyId())) {
                query.setParameter("surveyId", surveyResult.getSurveyId());
            }
            if (!Comparator.isEqualNull(surveyResult.getQuestionId())) {
                query.setParameter("questionId", surveyResult.getQuestionId());
            }
            if (!Comparator.isEqualNull(surveyResult.getOfficerId())) {
                query.setParameter("officerId", surveyResult.getOfficerId());
            }
            if (!Comparator.isEqualNullOrEmpty(surveyResult.getAnswer())) {
                query.setParameter("answer", surveyResult.getAnswer());
            }
            if (!Comparator.isEqualNull(surveyResult.getLearnFromDate())) {
                query.setParameter("learnFromDate", surveyResult.getLearnFromDate());
            }
            if (!Comparator.isEqualNull(surveyResult.getLearnToDate())) {
                query.setParameter("learnToDate", surveyResult.getLearnToDate());
            }
        }
        return query.getResultList();
    }

    public List<BigInteger> getOfficer(Long surveyId, Long questionId) {

        StringBuilder sql = new StringBuilder("SELECT DISTINCT s.officer_id FROM survey_results s");
        sql.append(" WHERE s.answer = 'Có' AND s.survey_id = :surveyId AND  s.question_id = :questionId ");
        Query query = em.createNativeQuery(sql.toString());
        query.setParameter("surveyId", surveyId);
        query.setParameter("questionId", questionId);
        List<BigInteger> listOfficers = query.getResultList();
        if (!Comparator.isEqualNullOrEmpty(listOfficers)) {
            return listOfficers;
        }
        return null;
    }

}
