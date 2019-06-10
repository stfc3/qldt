/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stfc.dto.CertificateOfficers;
import org.stfc.dto.Courses;
import org.stfc.dto.Officers;
import org.stfc.utils.Comparator;
import org.stfc.utils.StringUtils;

/**
 *
 * @author dongdv
 */
@Repository
public class OfficersRepositoryImpl {

    private static final Logger logger = LoggerFactory.getLogger(OfficersRepositoryImpl.class);
    @Autowired
    EntityManager em;

    public List<Officers> onSearch(Officers officers) {
        StringBuilder sql = new StringBuilder("SELECT * FROM officers u WHERE 1 = 1");
        if (!Comparator.isEqualNull(officers)) {
            if (!Comparator.isEqualNull(officers.getOfficerId())) {
                sql.append(" AND u.officer_id = :officerId");
            }
            if (!Comparator.isEqualNull(officers.getPositionId())) {
                sql.append(" AND u.position_id = :positionId");
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getFullName())) {
                sql.append(" AND u.full_name like :fullName escape '/'");
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getEmail())) {
                sql.append(" AND u.email = :email");
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getGender())) {
                sql.append(" AND u.gender = :gender");
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getMobile())) {
                sql.append(" AND u.mobile = :mobile");
            }
            if (!Comparator.isEqualNull(officers.getStatus())) {
                sql.append(" AND u.status = :status");
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getKeySearch())) {
                sql.append(" AND MATCH (u.full_name, u.last_name, u.first_name, u.email, u.mobile, u.gender) AGAINST (:keySearch)");
            }
        }
        Query query = em.createNativeQuery(sql.toString(), Officers.class);

        if (!Comparator.isEqualNull(officers)) {
            if (!Comparator.isEqualNull(officers.getOfficerId())) {
                query.setParameter("officerId", officers.getOfficerId());
            }
            if (!Comparator.isEqualNull(officers.getPositionId())) {
                query.setParameter("positionId", officers.getPositionId());
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getFullName())) {
                query.setParameter("fullName", "%" + StringUtils.escapeCharacter(officers.getFullName()) + "%");
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getEmail())) {
                query.setParameter("email", officers.getEmail());
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getMobile())) {
                query.setParameter("mobile", officers.getMobile());
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getGender())) {
                query.setParameter("gender", officers.getGender());
            }
            if (!Comparator.isEqualNull(officers.getStatus())) {
                query.setParameter("status", officers.getStatus());
            }
            if (!Comparator.isEqualNullOrEmpty(officers.getKeySearch())) {
                query.setParameter("keySearch", officers.getKeySearch());
            }
        }
        return query.getResultList();
    }

    public Officers findOfficerByUsername(String username) {
        if (!Comparator.isEqualNullOrEmpty(username)) {
            String sql = "SELECT o.*, p.position_name FROM officers o, users u, positions p WHERE o.user_id = u.id AND u.status = 'ACTIVE' AND o.status = 1 AND o.position_id = p.position_id AND p.status = 1 AND u.username = :username";
            Query query = em.createNativeQuery(sql, Officers.class);
            query.setParameter("username", username);
            List<Officers> listOfficers = query.getResultList();
            if (!Comparator.isEqualNullOrEmpty(listOfficers)) {
                return listOfficers.get(0);
            }
        }
        return null;
    }
    public List<CertificateOfficers> findCertificatesByOfficer(Long officerId) {
        if (!Comparator.isEqualNull(officerId)) {
            String sql = "SELECT oc.*, c.certificate_name FROM officer_certificate oc, certificates c WHERE oc.certificate_id = c.certificate_id AND oc.status = 1 AND c.status = 1 AND oc.officer_id = :officerId";
            Query query = em.createNativeQuery(sql, CertificateOfficers.class);
            query.setParameter("officerId", officerId);
            List<CertificateOfficers> listOfficerCertificate = query.getResultList();
            if (!Comparator.isEqualNullOrEmpty(listOfficerCertificate)) {
                return listOfficerCertificate;
            }
        }
        return null;
    }
    public List<Courses> findCoursesByOfficer(Long officerId) {
        if (!Comparator.isEqualNull(officerId)) {
            String sql = "SELECT * FROM courses c, officer_course oc WHERE oc.officer_id = c.officer_id AND oc.officer_id = :officerId";
            Query query = em.createNativeQuery(sql, Courses.class);
            query.setParameter("officerId", officerId);
            List<Courses> listCourses = query.getResultList();
            if (!Comparator.isEqualNullOrEmpty(listCourses)) {
                return listCourses;
            }
        }
        return null;
    }
}
