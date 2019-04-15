/**
 * 
 */
package org.stfc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stfc.dto.CertificatePosition;

/**
 * @author viettx
 *
 */
public interface CertificatePositionRepository extends JpaRepository<CertificatePosition, Long> {
	List<CertificatePosition> findByPositions(Long positionId);
}
