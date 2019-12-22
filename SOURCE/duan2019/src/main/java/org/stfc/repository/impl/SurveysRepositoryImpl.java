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
import org.stfc.utils.Constants;
import org.stfc.utils.DateTimeUtils;
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

    public List<ExportSurvey> exportSurvey(Date fromDate, Date toDate, String positionType, Long departmentId) {
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
            if (Constants.EXPORT.POSITION_TYPE_CC.equals(positionType)) {
                sql.append(", SUM(CASE WHEN q.questionCode = 'kien_thuc_ky_nang_chuyen_nganh' THEN 1 ELSE 0 END) AS ktknChuyenNganh");
                sql.append(", SUM(CASE WHEN q.questionCode = 'kien_thuc_ky_nang_lam_viec' THEN 1 ELSE 0 END) AS ktknLamViec");
                sql.append(", SUM(CASE WHEN q.questionCode = 'ky_nang_lanh_dao_cap_phong' THEN 1 ELSE 0 END) AS knldCapPhong");
                sql.append(", SUM(CASE WHEN q.questionCode = 'ky_nang_lanh_dao_cap_vu' THEN 1 ELSE 0 END) AS ktknCapVu");
                sql.append(", SUM(CASE WHEN q.questionCode = 'ky_nang_lanh_dao_thu_truong' THEN 1 ELSE 0 END) AS knldThuTruong");
            } else if (Constants.EXPORT.POSITION_TYPE_VC.equals(positionType)) {
                sql.append(", SUM(CASE WHEN q.questionCode = 'chuc_danh_nghe_nghiep_hang1' THEN 1 ELSE 0 END) AS cdnnHang1");
                sql.append(", SUM(CASE WHEN q.questionCode = 'chuc_danh_nghe_nghiep_hang2' THEN 1 ELSE 0 END) AS cdnnHang2");
                sql.append(", SUM(CASE WHEN q.questionCode = 'chuc_danh_nghe_nghiep_hang3' THEN 1 ELSE 0 END) AS cdnnHang3");
                sql.append(", SUM(CASE WHEN q.questionCode = 'chuc_danh_nghe_nghiep_hang4' THEN 1 ELSE 0 END) AS cdnnHang4");
                sql.append(", SUM(CASE WHEN q.questionCode = 'chuc_vu_quan_ly_cap_phong' THEN 1 ELSE 0 END) AS cvqlCapPhong");
                sql.append(", SUM(CASE WHEN q.questionCode = 'chuc_vu_quan_ly_cap_vu' THEN 1 ELSE 0 END) AS cvqlCapVu");
                sql.append(", SUM(CASE WHEN q.questionCode = 'boi_duong_bat_buoc' THEN 1 ELSE 0 END) AS bdbb");
            }
            sql.append(", SUM(CASE WHEN q.questionCode = 'quoc_phong_an_ninh' THEN 1 ELSE 0 END) AS qpan");
            sql.append(", SUM(CASE WHEN q.questionCode = 'ngoai_ngu' THEN 1 ELSE 0 END) AS ngoaiNgu");
            sql.append(", SUM(CASE WHEN q.questionCode = 'tin_hoc' THEN 1 ELSE 0 END) AS tinHoc");
            sql.append(", COUNT(sr.answer) AS tong");
            sql.append(", 0 AS danToc");
            sql.append(", SUM(CASE WHEN o.gender = 'FEMALE' THEN 1 ELSE 0 END) AS nu)");
            sql.append(" FROM Surveys s, SurveyResults sr, Questions q, Officers o,  Positions p");
            sql.append(" WHERE s.surveyId = sr.surveyId AND sr.questionId = q.questionId AND sr.officerId = o.officerId AND o.positionId = p.positionId");
            sql.append(" AND sr.answer='Có'");
            sql.append(" AND sr.learnFromDate >= DATE(:fromDate)");
            sql.append(" AND sr.learnToDate <= DATE(:toDate)");
            if (!Comparator.isEqualNullOrEmpty(positionType)) {
                sql.append(" AND p.positionType = :positionType");
            }
            if (!Comparator.isEqualNull(departmentId)) {
                sql.append(" AND o.departmentId = :departmentId");
            }
            if (Constants.EXPORT.POSITION_TYPE_CC.equals(positionType)) {
                sql.append(" AND q.questionCode IN ('ly_luan_chinh_tri_cao_cap','ly_luan_chinh_tri_trung_cap','ly_luan_chinh_tri_dang_vien_moi','ly_luan_chinh_tri_doi_tuong_ket_nap','quan_ly_nha_nuoc_chuyen_vien_cao_cap','quan_ly_nha_nuoc_chuyen_vien_chinh','quan_ly_nha_nuoc_chuyen_vien','quan_ly_nha_nuoc_can_su','chuyen_mon_tien_si','chuyen_mon_thac_si','chuyen_mon_dai_hoc','chuyen_mon_cao_dang','chuyen_mon_trung_cap','chuyen_mon_so_cap','kien_thuc_ky_nang_chuyen_nganh','kien_thuc_ky_nang_lam_viec','ky_nang_lanh_dao_cap_phong','ky_nang_lanh_dao_cap_vu','ky_nang_lanh_dao_thu_truong','quoc_phong_an_ninh','ngoai_ngu','tin_hoc')");
            } else if (Constants.EXPORT.POSITION_TYPE_VC.equals(positionType)) {
                sql.append(" AND q.questionCode IN ('ly_luan_chinh_tri_cao_cap','ly_luan_chinh_tri_trung_cap','ly_luan_chinh_tri_dang_vien_moi','ly_luan_chinh_tri_doi_tuong_ket_nap','quan_ly_nha_nuoc_chuyen_vien_cao_cap','quan_ly_nha_nuoc_chuyen_vien_chinh','quan_ly_nha_nuoc_chuyen_vien','quan_ly_nha_nuoc_can_su','chuyen_mon_tien_si','chuyen_mon_thac_si','chuyen_mon_dai_hoc','chuyen_mon_cao_dang','chuyen_mon_trung_cap','chuyen_mon_so_cap','chuc_danh_nghe_nghiep_hang1','chuc_danh_nghe_nghiep_hang2','chuc_danh_nghe_nghiep_hang3','chuc_danh_nghe_nghiep_hang4','chuc_vu_quan_ly_cap_phong','chuc_vu_quan_ly_cap_vu','boi_duong_bat_buoc','quoc_phong_an_ninh','ngoai_ngu','tin_hoc')");
            }
            sql.append(" GROUP BY p.positionType, p.positionName");
            Query query = em.createQuery(sql.toString());
            query.setParameter("fromDate", DateTimeUtils.convestDateToString(fromDate, Constants.DATE_FORMAT.YYYY_MM_DD));
            query.setParameter("toDate", DateTimeUtils.convestDateToString(toDate, Constants.DATE_FORMAT.YYYY_MM_DD));
            if (!Comparator.isEqualNullOrEmpty(positionType)) {
                query.setParameter("positionType", positionType);
            }
            if (!Comparator.isEqualNull(departmentId)) {
                query.setParameter("departmentId", departmentId);
            }
            List<ExportSurvey> listExportSurveys = query.getResultList();
            if (!Comparator.isEqualNullOrEmpty(listExportSurveys)) {
                return listExportSurveys;
            }
        }
        return null;
    }

    public List<ExportSurvey> exportCourse(Date fromDate, Date toDate, String positionType, Long departmentId) {
        if (!Comparator.isEqualNull(fromDate) && !Comparator.isEqualNull(toDate)) {
            StringBuilder sql = new StringBuilder("SELECT new org.stfc.entity.ExportSurvey (p.positionType, p.positionName");
            sql.append(", SUM(CASE WHEN c.courseName = 'Lý luận chính trị cao cấp' THEN 1 ELSE 0 END) AS llctCaoCap");
            sql.append(", SUM(CASE WHEN c.courseName = 'Lý luận chính trị trung cấp' THEN 1 ELSE 0 END) AS llctTrungCap");
            sql.append(", SUM(CASE WHEN c.courseName = 'Lý luận chính trị đảng viên mới' THEN 1 ELSE 0 END) AS llctDangVienMoi");
            sql.append(", SUM(CASE WHEN c.courseName = 'Lý luận chính trị đối tượng kết nạp' THEN 1 ELSE 0 END) AS llctKetNap");
            sql.append(", SUM(CASE WHEN c.courseName = 'Quản lý nhà nước chuyên viên cao cấp' THEN 1 ELSE 0 END) AS qlnnChuyenVienCaoCap");
            sql.append(", SUM(CASE WHEN c.courseName = 'Quản lý nhà nước chuyên viên chính' THEN 1 ELSE 0 END) AS qlnnChuyenVienChinh");
            sql.append(", SUM(CASE WHEN c.courseName = 'Quản lý nhà nước chuyên viên' THEN 1 ELSE 0 END) AS qlnnChuyenVien");
            sql.append(", SUM(CASE WHEN c.courseName = 'Quản lý nhà nước cán sự' THEN 1 ELSE 0 END) AS qlnnCanSu");
            sql.append(", SUM(CASE WHEN c.courseName = 'Chuyên môn tiến sĩ' THEN 1 ELSE 0 END) AS cmTienSi");
            sql.append(", SUM(CASE WHEN c.courseName = 'Chuyên môn thạc sĩ' THEN 1 ELSE 0 END) AS cmThacSi");
            sql.append(", SUM(CASE WHEN c.courseName = 'Chuyên môn đại học' THEN 1 ELSE 0 END) AS cmDaiHoc");
            sql.append(", SUM(CASE WHEN c.courseName = 'Chuyên môn cao đẳng' THEN 1 ELSE 0 END) AS cmCaoDang");
            sql.append(", SUM(CASE WHEN c.courseName = 'Chuyên môn trung cấp' THEN 1 ELSE 0 END) AS cmTrungCap");
            sql.append(", SUM(CASE WHEN c.courseName = 'Chuyên môn sơ cấp' THEN 1 ELSE 0 END) AS cmSoCap");
            if (Constants.EXPORT.POSITION_TYPE_CC.equals(positionType)) {
                sql.append(", SUM(CASE WHEN c.courseName = 'Kiến thức kỹ năng chuyên ngành' THEN 1 ELSE 0 END) AS ktknChuyenNganh");
                sql.append(", SUM(CASE WHEN c.courseName = 'Kiến thức kỹ năng làm việc' THEN 1 ELSE 0 END) AS ktknLamViec");
                sql.append(", SUM(CASE WHEN c.courseName = 'Kỹ năng lãnh đạo cấp phòng' THEN 1 ELSE 0 END) AS knldCapPhong");
                sql.append(", SUM(CASE WHEN c.courseName = 'Kỹ năng lãnh đạo cấp vụ' THEN 1 ELSE 0 END) AS ktknCapVu");
                sql.append(", SUM(CASE WHEN c.courseName = 'Kỹ năng lãnh đạo thủ trưởng' THEN 1 ELSE 0 END) AS knldThuTruong");
            } else if (Constants.EXPORT.POSITION_TYPE_VC.equals(positionType)) {
                sql.append(", SUM(CASE WHEN c.courseName = 'Chức danh nghề nghiệp hạng 1' THEN 1 ELSE 0 END) AS cdnnHang1");
                sql.append(", SUM(CASE WHEN c.courseName = 'Chức danh nghề nghiệp hạng 2' THEN 1 ELSE 0 END) AS cdnnHang2");
                sql.append(", SUM(CASE WHEN c.courseName = 'Chức danh nghề nghiệp hạng 3' THEN 1 ELSE 0 END) AS cdnnHang3");
                sql.append(", SUM(CASE WHEN c.courseName = 'Chức danh nghề nghiệp hạng 4' THEN 1 ELSE 0 END) AS cdnnHang4");
                sql.append(", SUM(CASE WHEN c.courseName = 'Chức vụ quản lý cấp phòng' THEN 1 ELSE 0 END) AS cvqlCapPhong");
                sql.append(", SUM(CASE WHEN c.courseName = 'Chức vụ quản lý cấp vụ' THEN 1 ELSE 0 END) AS cvqlCapVu");
                sql.append(", SUM(CASE WHEN c.courseName = 'Bồi dưỡng bắt buộc' THEN 1 ELSE 0 END) AS bdbb");
            }
            sql.append(", SUM(CASE WHEN c.courseName = 'Quốc phòng an ninh' THEN 1 ELSE 0 END) AS qpan");
            sql.append(", SUM(CASE WHEN c.courseName = 'Ngoại ngữ' THEN 1 ELSE 0 END) AS ngoaiNgu");
            sql.append(", SUM(CASE WHEN c.courseName = 'Tin học' THEN 1 ELSE 0 END) AS tinHoc");
            sql.append(", COUNT(*) AS tong");
            sql.append(", 0 AS danToc");
            sql.append(", SUM(CASE WHEN o.gender = 'FEMALE' THEN 1 ELSE 0 END) AS nu)");
            sql.append(" FROM Courses c, OfficerCourse oc, Officers o, Positions p");
            sql.append(" WHERE c.courseId = oc.courseId AND oc.officerId = o.officerId AND o.positionId = p.positionId");
            sql.append(" AND c.status = 1 AND oc.status = 1");
            sql.append(" AND c.startDate >= DATE(:fromDate)");
            sql.append(" AND c.endDate <= DATE(:toDate)");
            if (!Comparator.isEqualNullOrEmpty(positionType)) {
                sql.append(" AND p.positionType = :positionType");
            }
            if (!Comparator.isEqualNull(departmentId)) {
                sql.append(" AND o.departmentId = :departmentId");
            }
            if (Constants.EXPORT.POSITION_TYPE_CC.equals(positionType)) {
                sql.append(" AND c.courseName IN ('Lý luận chính trị cao cấp','Lý luận chính trị trung cấp','Lý luận chính trị đảng viên mới','Lý luận chính trị đối tượng kết nạp','Quản lý nhà nước chuyên viên cao cấp','Quản lý nhà nước chuyên viên chính','Quản lý nhà nước chuyên viên','Quản lý nhà nước cán sự','Chuyên môn tiến sĩ','Chuyên môn thạc sĩ','Chuyên môn đại học','Chuyên môn cao đẳng','Chuyên môn trung cấp','Chuyên môn sơ cấp','Kiến thức kỹ năng chuyên ngành','Kiến thức kỹ năng làm việc','Kỹ năng lãnh đạo cấp phòng','Kỹ năng lãnh đạo cấp vụ','Kỹ năng lãnh đạo thủ trưởng','Quốc phòng an ninh','Ngoại ngữ','Tin học')");
            } else if (Constants.EXPORT.POSITION_TYPE_VC.equals(positionType)) {
                sql.append(" AND c.courseName IN ('Lý luận chính trị cao cấp','Lý luận chính trị trung cấp','Lý luận chính trị đảng viên mới','Lý luận chính trị đối tượng kết nạp','Quản lý nhà nước chuyên viên cao cấp','Quản lý nhà nước chuyên viên chính','Quản lý nhà nước chuyên viên','Quản lý nhà nước cán sự','Chuyên môn tiến sĩ','Chuyên môn thạc sĩ','Chuyên môn đại học','Chuyên môn cao đẳng','Chuyên môn trung cấp','Chuyên môn sơ cấp','Chức danh nghề nghiệp hạng 1','Chức danh nghề nghiệp hạng 2','Chức danh nghề nghiệp hạng 3','Chức danh nghề nghiệp hạng 4','Chức vụ quản lý cấp phòng','Chức vụ quản lý cấp vụ','Bồi dưỡng bắt buộc','Quốc phòng an ninh','Ngoại ngữ','Tin học')");
            }
            sql.append(" GROUP BY p.positionType, p.positionName");
            Query query = em.createQuery(sql.toString());
            query.setParameter("fromDate", DateTimeUtils.convestDateToString(fromDate, Constants.DATE_FORMAT.YYYY_MM_DD));
            query.setParameter("toDate", DateTimeUtils.convestDateToString(toDate, Constants.DATE_FORMAT.YYYY_MM_DD));
            if (!Comparator.isEqualNullOrEmpty(positionType)) {
                query.setParameter("positionType", positionType);
            }
            if (!Comparator.isEqualNull(departmentId)) {
                query.setParameter("departmentId", departmentId);
            }
            List<ExportSurvey> listExportSurveys = query.getResultList();
            if (!Comparator.isEqualNullOrEmpty(listExportSurveys)) {
                return listExportSurveys;
            }
        }
        return null;
    }

}
