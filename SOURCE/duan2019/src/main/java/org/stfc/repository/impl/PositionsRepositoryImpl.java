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
import org.stfc.dto.Positions;
import org.stfc.utils.Comparator;

/**
 *
 * @author dongdv
 */
@Repository
public class PositionsRepositoryImpl {

    private static final Logger logger = LoggerFactory.getLogger(PositionsRepositoryImpl.class);
    @Autowired
    EntityManager em;

    public List<Positions> onSearch(Positions positions) {
        StringBuilder sql = new StringBuilder("SELECT * FROM positions u WHERE 1 = 1");
        if (!Comparator.isEqualNull(positions)) {
            if (!Comparator.isEqualNull(positions.getPositionId())) {
                sql.append(" AND u.position_id = :positionId");
            }
            if (!Comparator.isEqualNullOrEmpty(positions.getPositionType())) {
                sql.append(" AND u.position_type = :positionType");
            }
            if (!Comparator.isEqualNullOrEmpty(positions.getPositionName())) {
                sql.append(" AND u.position_name = :positionName");
            }
            if (!Comparator.isEqualNull(positions.getStatus())) {
                sql.append(" AND u.status = :status");
            }
        }
        Query query = em.createNativeQuery(sql.toString(), Positions.class);

        if (!Comparator.isEqualNull(positions)) {
            if (!Comparator.isEqualNull(positions.getPositionId())) {
                query.setParameter("positionId", positions.getPositionId());
            }
            if (!Comparator.isEqualNullOrEmpty(positions.getPositionType())) {
                query.setParameter("positionType",positions.getPositionType());
            }
            if (!Comparator.isEqualNullOrEmpty(positions.getPositionName())) {
                query.setParameter("positionName",positions.getPositionName());
            }
            if (!Comparator.isEqualNull(positions.getStatus())) {
                query.setParameter("status", positions.getStatus());
            }
        }
        return query.getResultList();
    }
}
