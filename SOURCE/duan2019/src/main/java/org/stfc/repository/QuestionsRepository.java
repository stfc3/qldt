/**
 * 
 */
package org.stfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stfc.dto.Questions;

/**
 * @author dongdv
 *
 */
public interface QuestionsRepository extends JpaRepository<Questions, Long> {

}
