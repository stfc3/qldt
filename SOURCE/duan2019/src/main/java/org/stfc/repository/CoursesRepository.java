/**
 * 
 */
package org.stfc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.stfc.dto.Courses;

/**
 * @author viettx
 *
 */
@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long>, JpaSpecificationExecutor<Courses> {
	List<Courses> findByLecturerId(Long lecturerId);

	Courses findCoursesById(Long coursesId);
}
