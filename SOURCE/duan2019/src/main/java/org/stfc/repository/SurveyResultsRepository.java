/**
 * 
 */
package org.stfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stfc.dto.SurveyResults;

/**
 * @author dongdv
 *
 */
public interface SurveyResultsRepository extends JpaRepository<SurveyResults, Long> {

}
