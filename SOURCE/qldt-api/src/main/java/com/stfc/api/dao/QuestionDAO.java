/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.api.dao;

import com.stfc.entity.Questions;
import com.stfc.utils.Comparator;
import com.stfc.utils.StringUtils;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dong.dv
 */
@Repository(value = "questionDAO")
public class QuestionDAO extends AbstractDAO<Long, Questions> {

    public List<Questions> findQuestions(Questions question) {
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
        Query query = getSession().createNativeQuery(sql.toString(), Questions.class);

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
