/**
 * 
 */
package org.stfc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.stfc.dto.Departments;

/**
 * @author viettx
 *
 */
@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, Long>{
	@Query("select d from Departments d where d.status = 1")
	List<Departments> findAllDepartmentsActive();
}
