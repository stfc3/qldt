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
import org.stfc.dto.Objects;
import org.stfc.utils.Comparator;

/**
 *
 * @author dongdv
 */
@Repository
public class ObjectsRepositoryImpl {

    private static final Logger logger = LoggerFactory.getLogger(ObjectsRepositoryImpl.class);
    @Autowired
    EntityManager em;

    public List<Objects> onSearch(Objects object) {
        StringBuilder sql = new StringBuilder("SELECT * FROM objects o WHERE 1 = 1");
        if (!Comparator.isEqualNull(object)) {
            if (!Comparator.isEqualNull(object.getObjectId())) {
                sql.append(" AND o.object_id = :objectId");
            }
            if (!Comparator.isEqualNullOrEmpty(object.getObjectCode())) {
                sql.append(" AND o.object_code = :objectCode");
            }
            if (!Comparator.isEqualNullOrEmpty(object.getObjectName())) {
                sql.append(" AND o.object_name = :objectName");
            }
            if (!Comparator.isEqualNullOrEmpty(object.getAppCode())) {
                sql.append(" AND o.app_code = :appCode");
            }
            if (!Comparator.isEqualNull(object.getObjectType())) {
                sql.append(" AND o.object_type = :objectType");
            }
            if (!Comparator.isEqualNull(object.getObjectParent())) {
                sql.append(" AND o.object_parent = :objectParent");
            }
            if (!Comparator.isEqualNull(object.getStatus())) {
                sql.append(" AND o.status = :status");
            }
            if (!Comparator.isEqualNullOrEmpty(object.getKeySearch())) {
                sql.append(" AND MATCH (o.object_code, o.object_name, o.app_code, o.description) AGAINST (:keySearch)");
            }
        }
        Query query =em.createNativeQuery(sql.toString(), Objects.class);
        
        if (!Comparator.isEqualNull(object)) {
            if (!Comparator.isEqualNull(object.getObjectId())) {
                query.setParameter("objectId", object.getObjectId());
            }
            if (!Comparator.isEqualNullOrEmpty(object.getObjectCode())) {
                query.setParameter("objectCode", object.getObjectCode());
            }
            if (!Comparator.isEqualNullOrEmpty(object.getObjectName())) {
                query.setParameter("objectName", object.getObjectName());
            }
            if (!Comparator.isEqualNullOrEmpty(object.getAppCode())) {
                query.setParameter("appCode", object.getAppCode());
            }
            if (!Comparator.isEqualNull(object.getObjectType())) {
                query.setParameter("objectType", object.getObjectType());
            }
            if (!Comparator.isEqualNull(object.getObjectParent())) {
                query.setParameter("objectParent", object.getObjectParent());
            }
            if (!Comparator.isEqualNull(object.getStatus())) {
                query.setParameter("status", object.getStatus());
            }
            if (!Comparator.isEqualNullOrEmpty(object.getKeySearch())) {
                query.setParameter("keySearch", object.getKeySearch());
            }
        }
        return query.getResultList();
    }

}
