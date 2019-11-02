/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.repository.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stfc.entity.DepartmentsView;
import org.stfc.utils.Comparator;

/**
 *
 * @author dongdv
 */
@Repository
public class DepartmentsRepositoryImpl {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentsRepositoryImpl.class);
    @Autowired
    EntityManager em;

    public List<DepartmentsView> findDepartmentStatistic(Date fromDate, Date toDate) {

        
        StringBuilder sql = new StringBuilder("SELECT DISTINCT d1.department_name, case when d2.department_id is not null then 'Đã import' else 'Chưa import' end as status");
        sql.append(" FROM departments d1 LEFT OUTER JOIN (select d.department_id FROM  survey_results s, officers o, departments d ");
        sql.append(" where s.officer_id = o.officer_id and o.department_id = d.department_id and o.status = 1 and d.status = 1 and s.created_date >= :fromDate and s.created_date <= :toDate) d2 ");
        sql.append(" on d1.department_id= d2.department_id where d1.status=1 order by d1.department_name");
        Query query = em.createNativeQuery(sql.toString());
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
        List<DepartmentsView> listDepartmentsView = query.getResultList();
        if (!Comparator.isEqualNullOrEmpty(listDepartmentsView)) {
            return listDepartmentsView;
        }
        return null;
    }

}
