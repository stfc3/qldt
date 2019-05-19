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
import org.stfc.dto.Users;
import org.stfc.utils.Comparator;
import org.stfc.utils.StringUtils;

/**
 *
 * @author dmin
 */
@Repository
public class UsersRepositoryImpl {

    private static final Logger logger = LoggerFactory.getLogger(UsersRepositoryImpl.class);
    @Autowired
    EntityManager em;

    public List<Users> onSearch(Users user) {
        StringBuilder sql = new StringBuilder("SELECT * FROM users u WHERE 1 = 1");
        if (!Comparator.isEqualNull(user)) {
            if (!Comparator.isEqualNull(user.getUserId())) {
                sql.append(" AND u.user_id = :userId");
            }
            if (!Comparator.isEqualNullOrEmpty(user.getFullName())) {
                sql.append(" AND full_name like :fullName escape '/'");
            }
            if (!Comparator.isEqualNullOrEmpty(user.getUserName())) {
                sql.append(" AND u.user_name = :username");
            }
            if (!Comparator.isEqualNullOrEmpty(user.getEmail())) {
                sql.append(" AND u.email = :email");
            }
            if (!Comparator.isEqualNullOrEmpty(user.getMobile())) {
                sql.append(" AND u.mobile = :mobile");
            }
            if (!Comparator.isEqualNull(user.getUserType())) {
                sql.append(" AND u.user_type = :userType");
            }
            if (!Comparator.isEqualNull(user.getStatus())) {
                sql.append(" AND u.status = :status");
            }
            if (!Comparator.isEqualNullOrEmpty(user.getKeySearch())) {
                sql.append(" AND MATCH (u.user_name, u.full_name, u.email, u.description) AGAINST (:keySearch)");
            }
        }
        Query query =em.createNativeQuery(sql.toString(), Users.class);
        
        if (!Comparator.isEqualNull(user)) {
            if (!Comparator.isEqualNull(user.getUserId())) {
                query.setParameter("userId", user.getUserId());
            }
            if (!Comparator.isEqualNullOrEmpty(user.getFullName())) {
                query.setParameter("fullName", "%" + StringUtils.escapeCharacter(user.getFullName()) + "%");
            }
            if (!Comparator.isEqualNullOrEmpty(user.getUserName())) {
                query.setParameter("username", user.getUserName());
            }
            if (!Comparator.isEqualNullOrEmpty(user.getEmail())) {
                query.setParameter("email", user.getEmail());
            }
            if (!Comparator.isEqualNullOrEmpty(user.getMobile())) {
                query.setParameter("mobile", user.getMobile());
            }
            if (!Comparator.isEqualNull(user.getUserType())) {
                query.setParameter("userType", user.getUserType());
            }
            if (!Comparator.isEqualNull(user.getStatus())) {
                query.setParameter("status", user.getStatus());
            }
            if (!Comparator.isEqualNullOrEmpty(user.getKeySearch())) {
                query.setParameter("keySearch", user.getKeySearch());
            }
        }
        return query.getResultList();
    }
}
