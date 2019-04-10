/**
 *
 */
package org.stfc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.stfc.dto.UserRole;

/**
 * @author dongdv
 *
 */
public interface UserRolesRepository extends JpaRepository<UserRole, Long> {

}
