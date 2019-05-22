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
import org.stfc.dto.Answers;
import org.stfc.utils.Comparator;
import org.stfc.utils.StringUtils;

/**
 *
 * @author dongdv
 */
@Repository
public class AnswersRepositoryImpl {

    private static final Logger logger = LoggerFactory.getLogger(AnswersRepositoryImpl.class);
    @Autowired
    EntityManager em;

    public List<Answers> onSearch(Answers answer) {
        StringBuilder sql = new StringBuilder("SELECT * FROM answers u WHERE 1 = 1");
        if (!Comparator.isEqualNull(answer)) {
            if (!Comparator.isEqualNull(answer.getAnswerId())) {
                sql.append(" AND u.answer_id = :answerId");
            }
            if (!Comparator.isEqualNullOrEmpty(answer.getAnswerName())) {
                sql.append(" AND u.answer_name like :answerName escape '/'");
            }
            if (!Comparator.isEqualNull(answer.getQuestionId())) {
                sql.append(" AND u.question_id = :questionId");
            }
            if (!Comparator.isEqualNullOrEmpty(answer.getKeySearch())) {
                sql.append(" AND MATCH (u.answer_name) AGAINST (:keySearch)");
            }
        }
        Query query = em.createNativeQuery(sql.toString(), Answers.class);

        if (!Comparator.isEqualNull(answer)) {
            if (!Comparator.isEqualNull(answer.getAnswerId())) {
                query.setParameter("answerId", answer.getAnswerId());
            }
            if (!Comparator.isEqualNullOrEmpty(answer.getAnswerName())) {
                query.setParameter("answerName", "%" + StringUtils.escapeCharacter(answer.getAnswerName()) + "%");
            }
            if (!Comparator.isEqualNull(answer.getQuestionId())) {
                query.setParameter("questionId", answer.getQuestionId());
            }
            if (!Comparator.isEqualNullOrEmpty(answer.getKeySearch())) {
                query.setParameter("keySearch", answer.getKeySearch());
            }
        }
        return query.getResultList();
    }
}
