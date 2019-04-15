/**
 * 
 */
package org.stfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stfc.dto.Certificate;

/**
 * @author viettx
 *
 */
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

}
