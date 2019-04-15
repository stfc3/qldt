/**
 * 
 */
package org.stfc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.stfc.dto.Certificate;

/**
 * @author viettx
 *
 */
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
	@Query("select c  from Certificate c where c.status=1")
	List<Certificate> findByActive();
}
