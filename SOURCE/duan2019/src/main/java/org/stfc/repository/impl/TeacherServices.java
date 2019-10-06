/**
 * 
 */
package org.stfc.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stfc.dto.Schedule;
import org.stfc.dto.Teacher;
import org.stfc.entity.ScheduleEntity;
import org.stfc.repository.TeacherRepository;
import org.stfc.utils.Comparator;

/**
 * @author viettx
 *
 */
@Service
public class TeacherServices {
	private static final Logger logger = LoggerFactory.getLogger(TeacherServices.class);

	@Autowired
	EntityManager em;
	@Autowired
	TeacherRepository repository;

	public List<Teacher> findAllTeacher(int status) {
		return repository.findAllByStatus(status);
	}

	public List<ScheduleEntity> findAllTeacherByClassCode(String classCode) {
		try {
			if (!Comparator.isEqualNullOrEmpty(classCode)) {
//				StringBuilder builder = new StringBuilder(
//						"SELECT new org.stfc.entity.ScheduleEntity (DISTINCT st.code as code, st.name as teacherName, c.name as className)");
//				builder.append(
//						" from Schedule s, ClassRoom c, Teacher st where s.classCode = c.code and s.staffId = st.code");
//				builder.append(" and st.status =1 and s.classCode = :classCode");

				StringBuilder builder = new StringBuilder(
						"select DISTINCT st.staff_code, st.staff_name, c.class_name from sch_schedule s, sch_class c,");
				builder.append(
						" staff st where s.class_code = c.code and c.status = 1 and s.staff_id = st.staff_code and st.status = 1");
				builder.append(" and s.class_code = :classCode");
				Query query = em.createNativeQuery(builder.toString());
				query.setParameter("classCode", classCode);

				List<ScheduleEntity> listData = query.getResultList();
				return listData;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
