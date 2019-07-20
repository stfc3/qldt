/**
 * 
 */
package org.stfc.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stfc.dto.Courses;
import org.stfc.repository.CoursesRepository;

/**
 * @author viett
 *
 */
@Service
public class CourseServices {
	@Autowired
	CoursesRepository coursesRepository;

	public Courses findCoursesById(Long id) {
		return coursesRepository.findCoursesById(id);
	}

	public void deleteById(Long id) {
		coursesRepository.deleteById(id);
	}

	public void save(Courses courses) {
		coursesRepository.save(courses);
	}
	
	public List<Courses> findAllCourse(Courses courses){
		return coursesRepository.findAll();
	}

}
