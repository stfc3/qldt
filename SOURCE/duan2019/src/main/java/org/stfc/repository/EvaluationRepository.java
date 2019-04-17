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
import org.stfc.dto.Evaluations;

/**
 * @author viettx
 *
 */
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluations, Long>, JpaSpecificationExecutor<Evaluations> {
	List<Evaluations> findByCourseId(Long courseId);

	@Query("select e from Evaluations e where e.evaluationId = :evaluationId")
	Evaluations findEvaluationsById(@Param("evaluationId") Long evaluationsId);
}
