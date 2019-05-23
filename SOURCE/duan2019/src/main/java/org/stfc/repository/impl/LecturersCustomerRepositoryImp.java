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
import org.stfc.message.LecturersRequest;
import org.stfc.utils.Comparator;

/**
 * @author viett
 *
 */
@Repository
public class LecturersCustomerRepositoryImp {
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

	public List<Lecturers> onSearch(LecturersRequest request) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Lecturers> cq = cb.createQuery(Lecturers.class);
		Root<Lecturers> lecturers = cq.from(Lecturers.class);
		List<Predicate> predicates = new ArrayList<>();
		if (!Comparator.isEqualNull(request)) {
			if (!Comparator.isEqualNullOrEmpty(request.getFullName())) {
				predicates.add(cb.like(lecturers.get("fullName"), "%" + request.getFullName() + "%"));
			}
			if (!Comparator.isEqualNullOrEmpty(request.getGender())) {
				predicates.add(cb.equal(lecturers.get("gender"), request.getGender()));
			}
			if (!Comparator.isEqualNullOrEmpty(request.getPhone())) {
				predicates.add(cb.equal(lecturers.get("mobile"), request.getPhone()));
			}
			if (!Comparator.isEqualNullOrEmpty(request.getEmail())) {
				predicates.add(cb.equal(lecturers.get("email"), request.getEmail()));
			}
			if (!Comparator.isEqualNull(request.getDepId())) {
				predicates.add(cb.equal(lecturers.get("deptId"), request.getDepId()));
			}
			if (!Comparator.isEqualNull(request.getStauts())) {
				predicates.add(cb.equal(lecturers.get("status"), request.getStauts()));
			}
			if (!Comparator.isEqualNull(request.getPosId())) {
				predicates.add(cb.equal(lecturers.get("posiId"), request.getPosId()));
			}
		}
		logger.info("List predicates {}", predicates.size());
		cq.where(predicates.toArray(new Predicate[0]));
		return em.createQuery(cq).getResultList();

	}

}
