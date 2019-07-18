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
import org.stfc.dto.Questions;
import org.stfc.utils.Comparator;
import org.stfc.utils.StringUtils;

/**
 *
 * @author dongdv
 */
@Repository
public class QuestionsRepositoryImpl {

    private static final Logger logger = LoggerFactory.getLogger(QuestionsRepositoryImpl.class);
    @Autowired
    EntityManager em;

    public List<Questions> onSearch(Questions question) {
        StringBuilder sql = new StringBuilder("SELECT * FROM questions u WHERE 1 = 1");
        if (!Comparator.isEqualNull(question)) {
            if (!Comparator.isEqualNull(question.getSurveyId())) {
                sql.append(" AND u.survey_id = :surveyId");
            }
            if (!Comparator.isEqualNullOrEmpty(question.getQuestionContent())) {
                sql.append(" AND u.question_content like :questionContent escape '/'");
            }
            if (!Comparator.isEqualNullOrEmpty(question.getQuestionCode())) {
                sql.append(" AND u.question_code = :questionCode");
            }
            if (!Comparator.isEqualNull(question.getQuestionId())) {
                sql.append(" AND u.question_id = :questionId");
            }
            if (!Comparator.isEqualNull(question.getQuestionType())) {
                sql.append(" AND u.question_type = :questionType");
            }
            if (!Comparator.isEqualNullOrEmpty(question.getKeySearch())) {
                sql.append(" AND MATCH (u.question_content) AGAINST (:keySearch)");
            }
        }
        Query query = em.createNativeQuery(sql.toString(), Questions.class);

        if (!Comparator.isEqualNull(question)) {
            if (!Comparator.isEqualNull(question.getSurveyId())) {
                query.setParameter("surveyId", question.getSurveyId());
            }
            if (!Comparator.isEqualNullOrEmpty(question.getQuestionContent())) {
                query.setParameter("questionContent", "%" + StringUtils.escapeCharacter(question.getQuestionContent()) + "%");
            }
            if (!Comparator.isEqualNullOrEmpty(question.getQuestionCode())) {
                query.setParameter("questionCode", question.getQuestionCode());
            }
            if (!Comparator.isEqualNull(question.getQuestionId())) {
                query.setParameter("questionId", question.getQuestionId());
            }
            if (!Comparator.isEqualNull(question.getQuestionType())) {
                query.setParameter("questionType", question.getQuestionType());
            }
            if (!Comparator.isEqualNullOrEmpty(question.getKeySearch())) {
                query.setParameter("keySearch", question.getKeySearch());
            }
        }
        return query.getResultList();
    }
}
