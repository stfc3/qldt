/**
 * 
 */
package org.stfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stfc.dto.Officers;

/**
 * @author dongdv
 *
 */
public interface OfficersRepository extends JpaRepository<Officers, Long> {

}
