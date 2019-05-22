/**
 * 
 */
package org.stfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stfc.dto.Surveys;

/**
 * @author dongdv
 *
 */
public interface SurveysRepository extends JpaRepository<Surveys, Long> {

}
