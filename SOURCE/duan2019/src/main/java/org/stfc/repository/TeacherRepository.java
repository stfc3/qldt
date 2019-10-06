/**
 * 
 */
package org.stfc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.stfc.dto.Teacher;

/**
 * @author viettx
 *
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	public List<Teacher> findAllByStatus(int status);
}
