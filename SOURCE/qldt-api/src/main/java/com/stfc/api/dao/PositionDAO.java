/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.api.dao;

import com.stfc.entity.Positions;
import com.stfc.utils.Comparator;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dong.dv
 */
@Repository(value = "positionDAO")
public class PositionDAO extends AbstractDAO<Long, Positions> {
    
    public List<Positions> findPositions(Positions positions) {
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
        Query query = getSession().createNativeQuery(sql.toString(), Positions.class);

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
