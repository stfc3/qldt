/**
 *
 */
package org.stfc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.stfc.dto.Users;

/**
 * @author dongdv
 *
 */
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("select a from Users a")
    List<Users> findAllUser();

    @Query("SELECT u FROM Users u WHERE u.userName = :userName AND u.status = 1")
    List<Users> findUserByUserName(@Param("userName") String userName);

}
