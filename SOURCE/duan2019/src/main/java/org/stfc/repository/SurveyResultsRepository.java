/**
 * 
 */
package org.stfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stfc.dto.SurveyResults;

/**
 * @author viettx
 *
 */
public interface SurveyResultsRepository extends JpaRepository<SurveyResults, Long> {

}
