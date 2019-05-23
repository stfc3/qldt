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
import org.stfc.dto.Roles;
import org.stfc.utils.Comparator;

/**
 *
 * @author dongdv
 */
@Repository
public class RolesRepositoryImpl {

    private static final Logger logger = LoggerFactory.getLogger(RolesRepositoryImpl.class);
    @Autowired
    EntityManager em;

    public List<Roles> onSearch(Roles role) {
        StringBuilder sql = new StringBuilder("SELECT * FROM roles r WHERE 1 = 1");
        if (!Comparator.isEqualNull(role)) {
            if (!Comparator.isEqualNull(role.getRoleId())) {
                sql.append(" AND r.role_id = :roleId");
            }
            if (!Comparator.isEqualNullOrEmpty(role.getRoleCode())) {
                sql.append(" AND r.role_code = :roleCode");
            }
            if (!Comparator.isEqualNullOrEmpty(role.getRoleCode())) {
                sql.append(" AND r.role_name = :roleName");
            }
            if (!Comparator.isEqualNull(role.getStatus())) {
                sql.append(" AND r.status = :status");
            }
            if (!Comparator.isEqualNullOrEmpty(role.getKeySearch())) {
                sql.append(" AND MATCH (r.role_code, r.role_name, r.description) AGAINST (:keySearch)");
            }
        }
        Query query =em.createNativeQuery(sql.toString(), Roles.class);
        
        if (!Comparator.isEqualNull(role)) {
            if (!Comparator.isEqualNull(role.getRoleId())) {
                query.setParameter("roleId", role.getRoleId());
            }
            if (!Comparator.isEqualNullOrEmpty(role.getRoleCode())) {
                query.setParameter("roleCode", role.getRoleCode());
            }
            if (!Comparator.isEqualNullOrEmpty(role.getRoleName())) {
                query.setParameter("roleName", role.getRoleName());
            }
            if (!Comparator.isEqualNull(role.getStatus())) {
                query.setParameter("status", role.getStatus());
            }
            if (!Comparator.isEqualNullOrEmpty(role.getKeySearch())) {
                query.setParameter("keySearch", role.getKeySearch());
            }
        }
        return query.getResultList();
    }
}
