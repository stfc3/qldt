/**
 *
 */
package org.stfc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.stfc.dto.Roles;

/**
 * @author dongdv
 *
 */
public interface RolesRepository extends JpaRepository<Roles, Long> {

}
