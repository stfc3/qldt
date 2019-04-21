/**
 * 
 */
package org.stfc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.stfc.dto.Positions;

/**
 * @author viettx
 *
 */
@Repository
public interface PositionsRepository extends JpaRepository<Positions, Long>{
	@Query("select p from Positions p where p.status = 1")
	List<Positions> findAllPositionsActive();
	
	
	@Query("select p from Positions p where p.status = 1 and p.id = :positionId")
	Positions findByPositionId(Long positionId);
}
