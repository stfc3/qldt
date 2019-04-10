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
import org.stfc.dto.Roles;
import org.stfc.utils.Comparator;

/**
 *
 * @author dmin
 */
@Repository
public class RolesRepositoryImpl{

    private static final Logger logger = LoggerFactory.getLogger(RolesRepositoryImpl.class);
    @Autowired
    EntityManager em;

    public List<Roles> onSearch(Roles roles) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Roles> cq = cb.createQuery(Roles.class);
        Root<Roles> listRoles = cq.from(Roles.class);
        List<Predicate> predicates = new ArrayList<>();
        if (!Comparator.isEqualNullOrEmpty(roles.getRoleName())) {
            predicates.add(cb.like(listRoles.get("roleName"), "%" + roles.getRoleName() + "%"));
        }
        if (!Comparator.isEqualNullOrEmpty(roles.getRoleCode())) {
            predicates.add(cb.equal(listRoles.get("roleCode"), roles.getRoleCode()));
        }
        if (!Comparator.isEqualNull(roles.getStatus())) {
            predicates.add(cb.equal(listRoles.get("status"), roles.getStatus()));
        }
        logger.info("List predicates {}", predicates.size());
        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();

    }

}
