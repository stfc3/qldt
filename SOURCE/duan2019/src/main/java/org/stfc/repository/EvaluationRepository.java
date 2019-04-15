/**
 * 
 */
package org.stfc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.stfc.dto.Evaluations;

/**
 * @author viettx
 *
 */
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluations, Long>, JpaSpecificationExecutor<Evaluations> {
	List<Evaluations> findByCourseId(Long courseId);

	Evaluations findEvaluationsById(Long evaluationsId);
}
