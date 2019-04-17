/**
 * 
 */
package org.stfc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.stfc.dto.CertificatePosition;

/**
 * @author viettx
 *
 */
public interface CertificatePositionRepository extends JpaRepository<CertificatePosition, Long> {
	@Query("select c from CertificatePosition c where c.position = :position")
	List<CertificatePosition> findByPositions(@Param("position") Long positionId);
}
