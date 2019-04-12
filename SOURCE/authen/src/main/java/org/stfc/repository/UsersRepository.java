/**
 *
 */
package org.stfc.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.stfc.dto.Objects;
import org.stfc.dto.Users;

/**
 * @author dongdv
 *
 */
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("select a from Users a")
    List<Users> findAllUser();

    @Query("SELECT u FROM Users u WHERE u.userName = :username AND password = :password AND u.status = 1")
    Users login(@Param("username") String username, @Param("password") String password);
    @Query("SELECT o FROM Objects o, RoleObject ro, UserRole ur WHERE ur.userId = :userId AND ur.roleId = ro.roleId AND ro.objectId = o.objectId AND o.status = 1 AND ur.status = 1 AND ro.status = 1")
    Objects getObjectByUser(@Param("userId") BigInteger userId);
    
    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.password = :password, u.passwordChanged = 1, updatedDate = now() WHERE u.userId = :userId")
    void changePass(@Param("userId") Long userId, @Param("password") String password);

}
