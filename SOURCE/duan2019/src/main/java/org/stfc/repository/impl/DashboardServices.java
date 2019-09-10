/**
 * 
 */
package org.stfc.repository.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stfc.entity.HeaderEntity;
import org.stfc.entity.TopCourses;
import org.stfc.entity.TopLecturer;
import org.stfc.utils.Comparator;

/**
 * @author viettx
 *
 */
@Service
public class DashboardServices {
	private static final Logger logger = LoggerFactory.getLogger(DashboardServices.class);
	@Autowired
	EntityManager em;

	public List<TopLecturer> topLecturer() {
		try {
			StringBuilder builder = new StringBuilder(
					"SELECT new org.stfc.entity.TopLecturer(e.evaluationId as evaluation, c.courseId as course, e.leturerPoint as point, e.lecturerComment as comment, ");
			builder.append(
					" l.fistName as firstName, l.lastName as lastName, l.fullName as fullName, l.mobile as phone, l.deptId as department ");
			builder.append(
					" ) from Evaluations e, Courses c, Lecturers l where e.courseId = c.courseId and c.lecturerId = l.id ");
			builder.append("order by e.leturerPoint desc ");
			logger.debug("SQL: {}", builder.toString());
			Query query = em.createQuery(builder.toString(), TopLecturer.class);
			List<TopLecturer> listData = query.getResultList();
			return listData;

		} catch (Exception e) {
			// TODO: handle exception
			logger.debug(e.getMessage(), e);
		}
		return null;
	}

	public List<TopCourses> topCourses() {
		try {
			StringBuilder builder = new StringBuilder(
					"SELECT new org.stfc.entity.TopCourses(e.evaluationId as evaluation, c.courseId as course, e.coursePoint as coursePoint, c.courseName as courseName, e.leturerPoint as point, e.lecturerComment as comment, ");
			builder.append(
					" l.fistName as firstName, l.lastName as lastName, l.fullName as fullName, l.mobile as phone, l.deptId as department ");
			builder.append(
					" ) from Evaluations e, Courses c, Lecturers l where e.courseId = c.courseId and c.lecturerId = l.id ");
			builder.append("order by e.leturerPoint desc ");
			logger.debug("SQL: {}", builder.toString());
			Query query = em.createQuery(builder.toString(), TopCourses.class);
			List<TopCourses> listData = query.getResultList();
			return listData;

		} catch (Exception e) {
			// TODO: handle exception
			logger.debug(e.getMessage(), e);
		}
		return null;
	}

	public Object[] getHeader() {
		try {
			StringBuilder builder = new StringBuilder("SELECT ");
			builder.append(" SUM(a.totalCourses)     AS totalCourses,");
			builder.append(" SUM(a.totalOfficer)     AS totalOfficer,");
			builder.append(" SUM(a.totalSurvey)      AS totalSurvey,");
			builder.append(" SUM(a.totalCoursesPlan) AS totalCoursesPlan ");
			builder.append(" FROM ( SELECT count(*) AS totalCourses, ");
			builder.append(" 		NULL     AS totalOfficer, ");
			builder.append("		NULL     AS totalSurvey, ");
			builder.append(" 		NULL     AS totalCoursesPlan ");
			builder.append(" 	FROM courses WHERE status = 1 ");
			builder.append(" 	UNION");
			builder.append(" 		SELECT NULL     AS totalCourses,");
			builder.append(" 		count(*) AS totalOfficer,");
			builder.append(" 		NULL     AS totalSurvey,");
			builder.append(" 		NULL     AS totalCoursesPlan");
			builder.append(" 		FROM officer_course oc, officers o where oc.officer_id = o.officer_id");
			builder.append(" 	UNION");
			builder.append(" 		SELECT NULL     AS totalCourses,");
			builder.append(" 		NULL     AS totalOfficer,");
			builder.append(" 		count(*) AS totalSurvey,");
			builder.append(" 		NULL     AS totalCoursesPlan");
			builder.append(" 	FROM survey_results");
			builder.append(" 	UNION");
			builder.append("		SELECT");
			builder.append(" 		NULL     AS totalCourses,");
			builder.append(" 		NULL     AS totalOfficer,");
			builder.append(" 		NULL     AS totalSurvey,");
			builder.append(" 		count(*) AS totalCoursesPlan");
			builder.append(" 	FROM courses WHERE status = 2) a");
			logger.debug("SQL: {}", builder.toString());
			Query query = em.createNativeQuery(builder.toString());
			List<Object[]> listData = query.getResultList();
			if (!Comparator.isEqualNullOrEmpty(listData)) {
				return listData.get(0);
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.debug(e.getMessage(), e);
		}
		return null;
	}
}
