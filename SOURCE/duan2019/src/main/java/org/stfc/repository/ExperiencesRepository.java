/**
 * 
 */
package org.stfc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.stfc.dto.Experiences;

/**
 * @author viettx
 *
 */
@Repository
public interface ExperiencesRepository extends JpaRepository<Experiences, Long>, JpaSpecificationExecutor<Experiences> {
	List<Experiences> findByLecturerId(Long lecturer);

	@Query("select e from Experiences e where e.experienceId = :experienceId")
	Experiences findExperiencesById(@Param("experienceId") Long experiencesId);
}
