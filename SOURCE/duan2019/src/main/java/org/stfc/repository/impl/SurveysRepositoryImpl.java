/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.repository.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stfc.dto.Surveys;
import org.stfc.entity.ExportSurvey;
import org.stfc.utils.Comparator;
import org.stfc.utils.StringUtils;

/**
 *
 * @author dongdv
 */
@Repository
public class SurveysRepositoryImpl {

    private static final Logger logger = LoggerFactory.getLogger(SurveysRepositoryImpl.class);
    @Autowired
    EntityManager em;
    public List<Surveys> onSearch(Surveys survey) {
        StringBuilder sql = new StringBuilder("SELECT * FROM surveys u WHERE 1 = 1");
        if (!Comparator.isEqualNull(survey)) {
            if (!Comparator.isEqualNull(survey.getSurveyId())) {
                sql.append(" AND u.survey_id = :surveyId");
            }
            if (!Comparator.isEqualNullOrEmpty(survey.getSurveyTitle())) {
                sql.append(" AND u.survey_title like :surveyTitle escape '/'");
            }
            if (!Comparator.isEqualNullOrEmpty(survey.getSurveyDescription())) {
                sql.append(" AND u.survey_description like :surveyDescription escape '/'");
            }
            if (!Comparator.isEqualNull(survey.getStartTime())) {
                sql.append(" AND u.start_time <= :startTime");
            }
            if (!Comparator.isEqualNull(survey.getEndTime())) {
                sql.append(" AND u.end_time <= :endTime");
            }
            if (!Comparator.isEqualNullOrEmpty(survey.getKeySearch())) {
                sql.append(" AND MATCH (u.survey_title, u.survey_description) AGAINST (:keySearch)");
            }
        }
        Query query = em.createNativeQuery(sql.toString(), Surveys.class);

        if (!Comparator.isEqualNull(survey)) {
            if (!Comparator.isEqualNull(survey.getSurveyId())) {
                query.setParameter("surveyId", survey.getSurveyId());
            }
            if (!Comparator.isEqualNullOrEmpty(survey.getSurveyTitle())) {
                query.setParameter("surveyTitle", "%" + StringUtils.escapeCharacter(survey.getSurveyTitle()) + "%");
            }
            if (!Comparator.isEqualNullOrEmpty(survey.getSurveyDescription())) {
                query.setParameter("surveyDescription", "%" + StringUtils.escapeCharacter(survey.getSurveyDescription()) + "%");
            }
            if (!Comparator.isEqualNull(survey.getStartTime())) {
                query.setParameter("startTime", survey.getStartTime());
            }
            if (!Comparator.isEqualNull(survey.getEndTime())) {
                query.setParameter("endTime", survey.getEndTime());
            }
            if (!Comparator.isEqualNullOrEmpty(survey.getKeySearch())) {
                query.setParameter("keySearch", survey.getKeySearch());
            }
        }
        return query.getResultList();
    }
    
    
    public List<ExportSurvey> exportSurvey(Date fromDate, Date toDate, String positionType) {
        if (!Comparator.isEqualNull(fromDate) && !Comparator.isEqualNull(toDate)) {
        	StringBuilder sql = new StringBuilder("SELECT new org.stfc.entity.ExportSurvey (p.positionType, p.positionName");
            sql.append(", SUM(CASE WHEN q.questionCode = 'ly_luan_chinh_tri_cao_cap' THEN 1 ELSE 0 END) AS llctCaoCap");
			sql.append(", SUM(CASE WHEN q.questionCode = 'ly_luan_chinh_tri_trung_cap' THEN 1 ELSE 0 END) AS llctTrungCap");
            sql.append(", SUM(CASE WHEN q.questionCode = 'ly_luan_chinh_tri_dang_vien_moi' THEN 1 ELSE 0 END) AS llctDangVienMoi");
            sql.append(", SUM(CASE WHEN q.questionCode = 'ly_luan_chinh_tri_doi_tuong_ket_nap' THEN 1 ELSE 0 END) AS llctKetNap");
            sql.append(", SUM(CASE WHEN q.questionCode = 'quan_ly_nha_nuoc_chuyen_vien_cao_cap' THEN 1 ELSE 0 END) AS qlnnChuyenVienCaoCap");
            sql.append(", SUM(CASE WHEN q.questionCode = 'quan_ly_nha_nuoc_chuyen_vien_chinh' THEN 1 ELSE 0 END) AS qlnnChuyenVienChinh");
            sql.append(", SUM(CASE WHEN q.questionCode = 'quan_ly_nha_nuoc_chuyen_vien' THEN 1 ELSE 0 END) AS qlnnChuyenVien");
            sql.append(", SUM(CASE WHEN q.questionCode = 'quan_ly_nha_nuoc_can_su' THEN 1 ELSE 0 END) AS qlnnCanSu");
            sql.append(", SUM(CASE WHEN q.questionCode = 'chuyen_mon_tien_si' THEN 1 ELSE 0 END) AS cmTienSi");
            sql.append(", SUM(CASE WHEN q.questionCode = 'chuyen_mon_thac_si' THEN 1 ELSE 0 END) AS cmThacSi");
            sql.append(", SUM(CASE WHEN q.questionCode = 'chuyen_mon_dai_hoc' THEN 1 ELSE 0 END) AS cmDaiHoc");
            sql.append(", SUM(CASE WHEN q.questionCode = 'chuyen_mon_cao_dang' THEN 1 ELSE 0 END) AS cmCaoDang");
            sql.append(", SUM(CASE WHEN q.questionCode = 'chuyen_mon_trung_cap' THEN 1 ELSE 0 END) AS cmTrungCap");
            sql.append(", SUM(CASE WHEN q.questionCode = 'chuyen_mon_so_cap' THEN 1 ELSE 0 END) AS cmSoCap");
            sql.append(", SUM(CASE WHEN q.questionCode = 'kien_thuc_ky_nang_chuyen_nganh' THEN 1 ELSE 0 END) AS ktknChuyenNganh");
            sql.append(", SUM(CASE WHEN q.questionCode = 'kien_thuc_ky_nang_lam_viec' THEN 1 ELSE 0 END) AS ktknLamViec");
            sql.append(", SUM(CASE WHEN q.questionCode = 'ky_nang_lanh_dao_cap_phong' THEN 1 ELSE 0 END) AS knldCapPhong");
            sql.append(", SUM(CASE WHEN q.questionCode = 'ky_nang_lanh_dao_cap_vu' THEN 1 ELSE 0 END) AS ktknCapVu");
            sql.append(", SUM(CASE WHEN q.questionCode = 'ky_nang_lanh_dao_thu_truong' THEN 1 ELSE 0 END) AS knldThuTruong");
            sql.append(", SUM(CASE WHEN q.questionCode = 'quoc_phong_an_ninh' THEN 1 ELSE 0 END) AS qpan");
            sql.append(", SUM(CASE WHEN q.questionCode = 'ngoai_ngu' THEN 1 ELSE 0 END) AS ngoaiNgu");
            sql.append(", SUM(CASE WHEN q.questionCode = 'tin_hoc' THEN 1 ELSE 0 END) AS tinHoc");
            sql.append(", COUNT(sr.answer) AS tong");
            sql.append(", 0 AS danToc");
            sql.append(", SUM(CASE WHEN o.gender = 'FEMALE' THEN 1 ELSE 0 END) AS nu)");
            sql.append(" FROM Surveys s, SurveyResults sr, Questions q, Officers o,  Positions p");
            sql.append(" WHERE s.surveyId = sr.surveyId AND sr.questionId = q.questionId AND sr.officerId = o.officerId AND o.positionId = p.positionId");
            sql.append(" AND sr.answer='Có'");
            sql.append(" AND sr.learnDate >= :fromDate");
            sql.append(" AND sr.learnDate <= :toDate");
            if(!Comparator.isEqualNullOrEmpty(positionType)){
            	sql.append(" AND p.positionType = :positionType");
            }
            sql.append(" AND q.questionCode IN ('ly_luan_chinh_tri_cao_cap','ly_luan_chinh_tri_trung_cap','ly_luan_chinh_tri_dang_vien_moi','ly_luan_chinh_tri_doi_tuong_ket_nap','quan_ly_nha_nuoc_chuyen_vien_cao_cap','quan_ly_nha_nuoc_chuyen_vien_chinh','quan_ly_nha_nuoc_chuyen_vien','quan_ly_nha_nuoc_can_su','chuyen_mon_tien_si','chuyen_mon_thac_si','chuyen_mon_dai_hoc','chuyen_mon_cao_dang','chuyen_mon_trung_cap','chuyen_mon_so_cap','kien_thuc_ky_nang_chuyen_nganh','kien_thuc_ky_nang_lam_viec','ky_nang_lanh_dao_cap_phong','ky_nang_lanh_dao_cap_vu','ky_nang_lanh_dao_thu_truong','quoc_phong_an_ninh','ngoai_ngu','tin_hoc')");
            sql.append(" GROUP BY p.positionType, p.positionName");
            Query query = em.createQuery(sql.toString());
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
            if(!Comparator.isEqualNullOrEmpty(positionType)){
            	query.setParameter("positionType", positionType);
            }
            List<ExportSurvey> listExportSurveys = query.getResultList();
            if (!Comparator.isEqualNullOrEmpty(listExportSurveys)) {
                return listExportSurveys;
            }
        }
        return null;
    }

}
