/**
 * 
 */
package org.stfc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.stfc.dto.Lecturers;

/**
 * @author viettx
 *
 */
@Repository
public interface LecturersRepository extends JpaRepository<Lecturers, Long>, JpaSpecificationExecutor<Lecturers> {
	@Query("select l from Lecturers l")
	List<Lecturers> findAllLecturers();

	@Query("select l from Lecturers l where id = :id")
	Lecturers findLecturersById(@Param("id") Long id);
	
	

}
