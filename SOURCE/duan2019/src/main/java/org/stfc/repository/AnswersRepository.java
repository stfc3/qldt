/**
 * 
 */
package org.stfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stfc.dto.Answers;

/**
 * @author dongdv
 *
 */
public interface AnswersRepository extends JpaRepository<Answers, Long> {

}
