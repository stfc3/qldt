/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.repository.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stfc.entity.CoursesView;
import org.stfc.utils.Comparator;

/**
 *
 * @author dongdv
 */
@Repository
public class CoursesRepositoryImpl {

    private static final Logger logger = LoggerFactory.getLogger(CoursesRepositoryImpl.class);
    @Autowired
    EntityManager em;

    public List<CoursesView> findAllCourses(Date fromDate, Date toDate) {
        List<CoursesView> listCourses = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT new org.stfc.entity.CoursesView(c.courseName, c.status, c.startDate, c.endDate, l.fullName)");
        sql.append(" FROM Courses c LEFT OUTER JOIN Lecturers l ON c.lecturerId = l.id");
        Query query = em.createQuery(sql.toString());
        List<CoursesView> listCoursesOpened = query.getResultList();
        if (!Comparator.isEqualNullOrEmpty(listCoursesOpened)) {
            listCourses.addAll(listCoursesOpened);
        }
        if (!Comparator.isEqualNull(fromDate) && !Comparator.isEqualNull(toDate)) {
            StringBuilder sqlPlan = new StringBuilder("SELECT new org.stfc.entity.CoursesView(q.questionContent, 0, count(*), min(s.learnFromDate), max(s.learnToDate))");
            sqlPlan.append(" FROM SurveyResults s, Questions q");
            sqlPlan.append(" WHERE s.questionId = q.questionId");
            sqlPlan.append(" AND s.answer='Có'");
            sqlPlan.append(" AND s.learnFromDate >= :fromDate");
            sqlPlan.append(" AND s.learnToDate <= :toDate");
            sqlPlan.append(" GROUP BY q.questionContent");
            Query queryPlan = em.createQuery(sqlPlan.toString());
            queryPlan.setParameter("fromDate", fromDate);
            queryPlan.setParameter("toDate", toDate);
            List<CoursesView> listCoursesPlan = queryPlan.getResultList();
            if (!Comparator.isEqualNullOrEmpty(listCoursesPlan)) {
                listCourses.addAll(listCoursesPlan);
            }
        }
        return listCourses;
    }

}
