/**
 * 
 */
package org.stfc.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stfc.dto.Lecturers;
import org.stfc.utils.Utils;

/**
 * @author viett
 *
 */
@Repository
public class LecturersCustomerRepositoryImp implements LecturersCustomerRepository {
	private static final Logger logger = LoggerFactory.getLogger(LecturersCustomerRepositoryImp.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.stfc.repository.impl.LecturersCustomerRepository#findByFullNameAndGender(
	 * java.lang.String, java.lang.String)
	 */
	@Autowired
	EntityManager em;

	@Override
	public List<Lecturers> findByFullNameAndGender(String fullName, String gender) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Lecturers> cq = cb.createQuery(Lecturers.class);
		Root<Lecturers> lecturers = cq.from(Lecturers.class);
		List<Predicate> predicates = new ArrayList<>();
		if (fullName != null && !"".equals(fullName)) {
			predicates.add(cb.like(lecturers.get("fullName"), "%" + fullName + "%"));
		}
		if (gender != null && !"".equals(gender)) {
			predicates.add(cb.equal(lecturers.get("gender"), gender));
		}
		logger.info("List predicates {}", predicates.size());
		cq.where(predicates.toArray(new Predicate[0]));
		return em.createQuery(cq).getResultList();
	}

	@Override
	public List<Lecturers> onSearch(String fullName, String gender, String phone, String email, Long deprtId,
			Long postionId, Integer status) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Lecturers> cq = cb.createQuery(Lecturers.class);
		Root<Lecturers> lecturers = cq.from(Lecturers.class);
		List<Predicate> predicates = new ArrayList<>();
		if (!Utils.getInstance().isEmpty(fullName)) {
			predicates.add(cb.like(lecturers.get("fullName"), "%" + fullName + "%"));
		}
		if (!Utils.getInstance().isEmpty(gender)) {
			predicates.add(cb.equal(lecturers.get("gender"), gender));
		}
		if (!Utils.getInstance().isEmpty(phone)) {
			predicates.add(cb.equal(lecturers.get("mobile"), phone));
		}
		if (!Utils.getInstance().isEmpty(email)) {
			predicates.add(cb.equal(lecturers.get("email"), email));
		}
		if (deprtId != null) {
			predicates.add(cb.equal(lecturers.get("deptId"), deprtId));
		}
		if (status != null) {
			predicates.add(cb.equal(lecturers.get("status"), status));
		}
		if (postionId != null) {
			predicates.add(cb.equal(lecturers.get("posiId"), postionId));
		}
		logger.info("List predicates {}", predicates.size());
		cq.where(predicates.toArray(new Predicate[0]));
		return em.createQuery(cq).getResultList();

	}

}
