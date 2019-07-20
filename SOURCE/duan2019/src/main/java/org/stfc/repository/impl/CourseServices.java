/**
 * 
 */
package org.stfc.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.stfc.dto.Courses;
import org.stfc.message.CoursesRequest;
import org.stfc.repository.CoursesRepository;
import org.stfc.specification.STFCSpecification;
import org.stfc.specification.SearchOperation;
import org.stfc.utils.Comparator;

/**
 * @author viettx
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
	private static final Logger logger = LoggerFactory.getLogger(CourseServices.class);
	@Autowired
	EntityManager em;
	@Autowired
	CoursesRepository repository;

	public Courses findCoursesById(Long courseId) {
		return repository.findCoursesById(courseId);
	}

	public void save(Courses courses) {
		repository.save(courses);
	}

	public void deleteById(Long courseId) {
		repository.deleteById(courseId);
	}

	public List<Courses> findAllCourse(CoursesRequest request) {
		try {
			STFCSpecification<Courses> stfcSpecification = new STFCSpecification<Courses>();
			StringBuilder query = new StringBuilder();
			query.append(stfcSpecification.getFieldNames(Courses.class, request.getCourseName()));
			
			if (!Comparator.isEqualNull(request.getStartDate())) {
				String fromQuery = "startDate" + SearchOperation.LESS_THAN_OR_EQUAL.getCode() + request.getStartDate();
				query.append(fromQuery);
			}
			if (!Comparator.isEqualNull(request.getEndDate())) {
				String toQuery = "endDate" + SearchOperation.GREATER_THAN_OR_EQUAL.getCode() + request.getEndDate();
				query.append(toQuery);
			}
			logger.debug("Query {}", query.toString());
			Specification<Courses> spec = stfcSpecification.getSpecification(SearchOperation.OR_PREDICATE_FLAG, query.toString());
			List<Courses> listData = repository.findAll(spec);
			return listData;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	@Autowired
	CoursesRepository coursesRepository;

	
	
	public List<Courses> findAllCourse(Courses courses){
		return coursesRepository.findAll();
	}

}
