/**
 * 
 */
package org.stfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stfc.dto.CertificateOfficers;

/**
 * @author viettx
 *
 */
public interface CertificateOfficeRepository extends JpaRepository<CertificateOfficers, Long> {

}
