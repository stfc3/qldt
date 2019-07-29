/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.api.dao;

import com.stfc.entity.Officers;
import com.stfc.utils.Comparator;
import com.stfc.utils.StringUtils;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dong.dv
 */
@Repository(value = "officerDAO")
public class OfficerDAO extends AbstractDAO<Long, Officers> {

    public Officers findOfficerByUsername(String username) {
        if (!Comparator.isEqualNullOrEmpty(username)) {
            StringBuilder sql = new StringBuilder("SELECT new com.stfc.entity.Officers (o.officerId, o.firstName");
            sql.append(", o.lastName, o.fullName, o.gender, o.mobile");
            sql.append(", o.email, o.positionId, o.status, o.createdDate");
            sql.append(", o.updatedDate, o.userId, o.departmentId");
            sql.append(", p.positionName, p.positionType)");
            sql.append(" FROM Officers o, Users u, Positions p");
            sql.append(" WHERE o.userId = u.id AND u.status = 'ACTIVE' AND o.status = 1 AND o.positionId = p.positionId AND p.status = 1 AND u.username = :username");
            Query query = getSession().createQuery(sql.toString());
            query.setParameter("username", username);
            List<Officers> listOfficers = query.getResultList();
            if (!Comparator.isEqualNullOrEmpty(listOfficers)) {
                return listOfficers.get(0);
            }
        }
        return null;
    }

    public List<Officers> findOfficers(Officers officers) {
        StringBuilder sql = new StringBuilder("SELECT * FROM officers u WHERE 1 = 1");
        if (!Comparator.isEqualNull(officers)) {
            if (!Comparator.isEqualNull(officers.getOfficerId())) {
                sql.append(" AND u.officer_id = :officerId");
            }
            if (!Comparator.isEqualNull(officers.getPositionId())) {
                sql.append(" AND u.position_id = :positionId");
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getFullName())) {
                sql.append(" AND u.full_name like :fullName escape '/'");
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getEmail())) {
                sql.append(" AND u.email = :email");
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getGender())) {
                sql.append(" AND u.gender = :gender");
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getMobile())) {
                sql.append(" AND u.mobile = :mobile");
            }
            if (!Comparator.isEqualNull(officers.getStatus())) {
                sql.append(" AND u.status = :status");
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getKeySearch())) {
                sql.append(
                        " AND MATCH (u.full_name, u.last_name, u.first_name, u.email, u.mobile, u.gender) AGAINST (:keySearch)");
            }
        }
        Query query = getSession().createNativeQuery(sql.toString(), Officers.class);

        if (!Comparator.isEqualNull(officers)) {
            if (!Comparator.isEqualNull(officers.getOfficerId())) {
                query.setParameter("officerId", officers.getOfficerId());
            }
            if (!Comparator.isEqualNull(officers.getPositionId())) {
                query.setParameter("positionId", officers.getPositionId());
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getFullName())) {
                query.setParameter("fullName", "%" + StringUtils.escapeCharacter(officers.getFullName()) + "%");
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getEmail())) {
                query.setParameter("email", officers.getEmail());
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getMobile())) {
                query.setParameter("mobile", officers.getMobile());
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getGender())) {
                query.setParameter("gender", officers.getGender());
            }
            if (!Comparator.isEqualNull(officers.getStatus())) {
                query.setParameter("status", officers.getStatus());
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getKeySearch())) {
                query.setParameter("keySearch", officers.getKeySearch());
            }
        }
        return query.getResultList();
    }

}
