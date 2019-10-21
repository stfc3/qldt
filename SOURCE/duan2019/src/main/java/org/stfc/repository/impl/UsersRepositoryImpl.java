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

/**
 *
 * @author dongdv
 */
@Repository
public class UsersRepositoryImpl {

    private static final Logger logger = LoggerFactory.getLogger(UsersRepositoryImpl.class);
    @Autowired
    EntityManager em;

    public Users getUserExist(String username) {
        if (!Comparator.isEqualNullOrEmpty(username)) {
            String sql = "SELECT * FROM users u WHERE username REGEXP '^(" + username + ")\\\\d+' OR username = :username ORDER BY username DESC";
            Query query = em.createNativeQuery(sql, Users.class);
            query.setParameter("username", username);
            List<Users> listUsers = query.getResultList();
            if (!Comparator.isEqualNullOrEmpty(listUsers)) {
                return listUsers.get(0);
            }
        }
        return null;
    }
}
