/**
 *
 */
package org.stfc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.stfc.dto.RoleObject;

/**
 * @author dongdv
 *
 */
public interface RoleObjectsRepository extends JpaRepository<RoleObject, Long> {

}
