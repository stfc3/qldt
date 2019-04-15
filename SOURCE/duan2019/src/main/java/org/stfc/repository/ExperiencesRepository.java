/**
 * 
 */
package org.stfc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.stfc.dto.Experiences;

/**
 * @author viettx
 *
 */
@Repository
public interface ExperiencesRepository extends JpaRepository<Experiences, Long>, JpaSpecificationExecutor<Experiences> {
	List<Experiences> findByLecturerId(Long lecturer);

	Experiences findExperiencesById(Long experiencesId);
}
