/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.repository.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stfc.dto.Users;
import org.stfc.utils.Comparator;

/**
 *
 * @author dmin
 */
@Repository
public class UsersRepositoryImpl{

    private static final Logger logger = LoggerFactory.getLogger(UsersRepositoryImpl.class);
    @Autowired
    EntityManager em;

    public List<Users> onSearch(Users user) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Users> cq = cb.createQuery(Users.class);
        Root<Users> listUser = cq.from(Users.class);
        List<Predicate> predicates = new ArrayList<>();
        if (!Comparator.isEqualNullOrEmpty(user.getFullName())) {
            predicates.add(cb.like(listUser.get("fullName"), "%" + user.getFullName() + "%"));
        }
        if (!Comparator.isEqualNullOrEmpty(user.getUserName())) {
            predicates.add(cb.equal(listUser.get("userName"), user.getUserName()));
        }
        if (!Comparator.isEqualNullOrEmpty(user.getEmail())) {
            predicates.add(cb.equal(listUser.get("email"), user.getEmail()));
        }
        if (!Comparator.isEqualNullOrEmpty(user.getMobile())) {
            predicates.add(cb.equal(listUser.get("mobile"), user.getMobile()));
        }
        if (!Comparator.isEqualNull(user.getUserType())) {
            predicates.add(cb.equal(listUser.get("userType"), user.getUserType()));
        }
        if (!Comparator.isEqualNull(user.getStatus())) {
            predicates.add(cb.equal(listUser.get("status"), user.getStatus()));
        }
        logger.info("List predicates {}", predicates.size());
        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();

    }

}
