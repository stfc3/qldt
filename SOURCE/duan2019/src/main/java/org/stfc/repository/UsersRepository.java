/**
 * 
 */
package org.stfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stfc.dto.Users;

/**
 * @author dongdv
 *
 */
public interface UsersRepository extends JpaRepository<Users, Integer> {

}
