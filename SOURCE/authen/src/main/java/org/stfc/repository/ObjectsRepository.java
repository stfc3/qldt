/**
 *
 */
package org.stfc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.stfc.dto.Objects;

/**
 * @author dongdv
 *
 */
public interface ObjectsRepository extends JpaRepository<Objects, Long> {

}
