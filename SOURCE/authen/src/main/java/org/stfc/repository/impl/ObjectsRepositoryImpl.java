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
import org.stfc.dto.Objects;
import org.stfc.utils.Comparator;

/**
 *
 * @author dmin
 */
@Repository
public class ObjectsRepositoryImpl {

    private static final Logger logger = LoggerFactory.getLogger(ObjectsRepositoryImpl.class);
    @Autowired
    EntityManager em;

    public List<Objects> onSearch(Objects objects) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Objects> cq = cb.createQuery(Objects.class);
        Root<Objects> listObjects = cq.from(Objects.class);
        List<Predicate> predicates = new ArrayList<>();
        if (!Comparator.isEqualNull(objects)) {
            if (!Comparator.isEqualNullOrEmpty(objects.getObjectName())) {
                predicates.add(cb.like(listObjects.get("objectName"), "%" + objects.getObjectName() + "%"));
            }
            if (!Comparator.isEqualNullOrEmpty(objects.getObjectCode())) {
                predicates.add(cb.equal(listObjects.get("objectCode"), objects.getObjectCode()));
            }
            if (!Comparator.isEqualNullOrEmpty(objects.getAppCode())) {
                predicates.add(cb.equal(listObjects.get("appCode"), objects.getAppCode()));
            }
            if (!Comparator.isEqualNull(objects.getObjectParent())) {
                predicates.add(cb.equal(listObjects.get("objectParent"), objects.getObjectParent()));
            }
            if (!Comparator.isEqualNull(objects.getObjectType())) {
                predicates.add(cb.equal(listObjects.get("objectType"), objects.getObjectType()));
            }
            if (!Comparator.isEqualNull(objects.getStatus())) {
                predicates.add(cb.equal(listObjects.get("status"), objects.getStatus()));
            }
        }
        logger.info("List predicates {}", predicates.size());
        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();

    }

}
