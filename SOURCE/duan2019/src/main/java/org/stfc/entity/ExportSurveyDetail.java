/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.stfc.utils.Constants;

/**
 *
 * @author dmin
 */
public class ExportSurveyDetail {

    private String fullName;
    private String mobile;
    private String email;
    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT.DD_MM_YYYY, timezone = Constants.DATE_FORMAT.TIMEZONE_HCM)
    private Date learnFromDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT.DD_MM_YYYY, timezone = Constants.DATE_FORMAT.TIMEZONE_HCM)
    private Date learnToDate;
    private String positionName;
    private String llctCaoCap;
    private String llctTrungCap;
    private String llctDangVienMoi;
    private String llctKetNap;
    private String qlnnChuyenVienCaoCap;
    private String qlnnChuyenVienChinh;
    private String qlnnChuyenVien;
    private String qlnnCanSu;
    private String cmTienSi;
    private String cmThacSi;
    private String cmDaiHoc;
    private String cmCaoDang;
    private String cmTrungCap;
    private String cmSoCap;
    private String ktknChuyenNganh;
    private String ktknLamViec;
    private String knldCapPhong;
    private String knldCapVu;
    private String knldThuTruong;
    private String qpan;
    private String ngoaiNgu;
    private String tinHoc;
    private String cdnnHang1;
    private String cdnnHang2;
    private String cdnnHang3;
    private String cdnnHang4;
    private String cvqlCapPhong;
    private String cvqlCapVu;
    private String bdbb;

    public ExportSurveyDetail(String fullName, String positionName, String mobile, String email, String gender, Date learnFromDate, Date learnToDate, String llctCaoCap, String llctTrungCap,
            String llctDangVienMoi, String llctKetNap, String qlnnChuyenVienCaoCap, String qlnnChuyenVienChinh,
            String qlnnChuyenVien, String qlnnCanSu, String cmTienSi, String cmThacSi, String cmDaiHoc, String cmCaoDang,
            String cmTrungCap, String cmSoCap, String ktknChuyenNganh, String ktknLamViec, String knldCapPhong, String knldCapVu,
            String knldThuTruong, String qpan, String ngoaiNgu, String tinHoc) {
        super();
        this.fullName = fullName;
        this.mobile = mobile;
        this.email = email;
        this.gender = gender;
        this.learnFromDate = learnFromDate;
        this.learnToDate = learnToDate;
        this.positionName = positionName;
        this.llctCaoCap = llctCaoCap;
        this.llctTrungCap = llctTrungCap;
        this.llctDangVienMoi = llctDangVienMoi;
        this.llctKetNap = llctKetNap;
        this.qlnnChuyenVienCaoCap = qlnnChuyenVienCaoCap;
        this.qlnnChuyenVienChinh = qlnnChuyenVienChinh;
        this.qlnnChuyenVien = qlnnChuyenVien;
        this.qlnnCanSu = qlnnCanSu;
        this.cmTienSi = cmTienSi;
        this.cmThacSi = cmThacSi;
        this.cmDaiHoc = cmDaiHoc;
        this.cmCaoDang = cmCaoDang;
        this.cmTrungCap = cmTrungCap;
        this.cmSoCap = cmSoCap;
        this.ktknChuyenNganh = ktknChuyenNganh;
        this.ktknLamViec = ktknLamViec;
        this.knldCapPhong = knldCapPhong;
        this.knldCapVu = knldCapVu;
        this.knldThuTruong = knldThuTruong;
        this.qpan = qpan;
        this.ngoaiNgu = ngoaiNgu;
        this.tinHoc = tinHoc;
    }

    public ExportSurveyDetail(String fullName, String positionName, String mobile, String email, String gender, Date learnFromDate, Date learnToDate, String llctCaoCap, String llctTrungCap, String llctDangVienMoi, String llctKetNap, String qlnnChuyenVienCaoCap, String qlnnChuyenVienChinh, String qlnnChuyenVien, String qlnnCanSu, String cmTienSi, String cmThacSi, String cmDaiHoc, String cmCaoDang, String cmTrungCap, String cmSoCap, String cdnnHang1, String cdnnHang2, String cdnnHang3, String cdnnHang4, String cvqlCapPhong, String cvqlCapVu, String bdbb, String qpan, String ngoaiNgu, String tinHoc) {
        this.fullName = fullName;
        this.mobile = mobile;
        this.email = email;
        this.gender = gender;
        this.learnFromDate = learnFromDate;
        this.learnToDate = learnToDate;
        this.positionName = positionName;
        this.llctCaoCap = llctCaoCap;
        this.llctTrungCap = llctTrungCap;
        this.llctDangVienMoi = llctDangVienMoi;
        this.llctKetNap = llctKetNap;
        this.qlnnChuyenVienCaoCap = qlnnChuyenVienCaoCap;
        this.qlnnChuyenVienChinh = qlnnChuyenVienChinh;
        this.qlnnChuyenVien = qlnnChuyenVien;
        this.qlnnCanSu = qlnnCanSu;
        this.cmTienSi = cmTienSi;
        this.cmThacSi = cmThacSi;
        this.cmDaiHoc = cmDaiHoc;
        this.cmCaoDang = cmCaoDang;
        this.cmTrungCap = cmTrungCap;
        this.cmSoCap = cmSoCap;
        this.qpan = qpan;
        this.ngoaiNgu = ngoaiNgu;
        this.tinHoc = tinHoc;
        this.cdnnHang1 = cdnnHang1;
        this.cdnnHang2 = cdnnHang2;
        this.cdnnHang3 = cdnnHang3;
        this.cdnnHang4 = cdnnHang4;
        this.cvqlCapPhong = cvqlCapPhong;
        this.cvqlCapVu = cvqlCapVu;
        this.bdbb = bdbb;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getLlctCaoCap() {
        return llctCaoCap;
    }

    public void setLlctCaoCap(String llctCaoCap) {
        this.llctCaoCap = llctCaoCap;
    }

    public String getLlctTrungCap() {
        return llctTrungCap;
    }

    public void setLlctTrungCap(String llctTrungCap) {
        this.llctTrungCap = llctTrungCap;
    }

    public String getLlctDangVienMoi() {
        return llctDangVienMoi;
    }

    public void setLlctDangVienMoi(String llctDangVienMoi) {
        this.llctDangVienMoi = llctDangVienMoi;
    }

    public String getLlctKetNap() {
        return llctKetNap;
    }

    public void setLlctKetNap(String llctKetNap) {
        this.llctKetNap = llctKetNap;
    }

    public String getQlnnChuyenVienCaoCap() {
        return qlnnChuyenVienCaoCap;
    }

    public void setQlnnChuyenVienCaoCap(String qlnnChuyenVienCaoCap) {
        this.qlnnChuyenVienCaoCap = qlnnChuyenVienCaoCap;
    }

    public String getQlnnChuyenVienChinh() {
        return qlnnChuyenVienChinh;
    }

    public void setQlnnChuyenVienChinh(String qlnnChuyenVienChinh) {
        this.qlnnChuyenVienChinh = qlnnChuyenVienChinh;
    }

    public String getQlnnChuyenVien() {
        return qlnnChuyenVien;
    }

    public void setQlnnChuyenVien(String qlnnChuyenVien) {
        this.qlnnChuyenVien = qlnnChuyenVien;
    }

    public String getQlnnCanSu() {
        return qlnnCanSu;
    }

    public void setQlnnCanSu(String qlnnCanSu) {
        this.qlnnCanSu = qlnnCanSu;
    }

    public String getCmTienSi() {
        return cmTienSi;
    }

    public void setCmTienSi(String cmTienSi) {
        this.cmTienSi = cmTienSi;
    }

    public String getCmThacSi() {
        return cmThacSi;
    }

    public void setCmThacSi(String cmThacSi) {
        this.cmThacSi = cmThacSi;
    }

    public String getCmDaiHoc() {
        return cmDaiHoc;
    }

    public void setCmDaiHoc(String cmDaiHoc) {
        this.cmDaiHoc = cmDaiHoc;
    }

    public String getCmCaoDang() {
        return cmCaoDang;
    }

    public void setCmCaoDang(String cmCaoDang) {
        this.cmCaoDang = cmCaoDang;
    }

    public String getCmTrungCap() {
        return cmTrungCap;
    }

    public void setCmTrungCap(String cmTrungCap) {
        this.cmTrungCap = cmTrungCap;
    }

    public String getCmSoCap() {
        return cmSoCap;
    }

    public void setCmSoCap(String cmSoCap) {
        this.cmSoCap = cmSoCap;
    }

    public String getKtknChuyenNganh() {
        return ktknChuyenNganh;
    }

    public void setKtknChuyenNganh(String ktknChuyenNganh) {
        this.ktknChuyenNganh = ktknChuyenNganh;
    }

    public String getKtknLamViec() {
        return ktknLamViec;
    }

    public void setKtknLamViec(String ktknLamViec) {
        this.ktknLamViec = ktknLamViec;
    }

    public String getKnldCapPhong() {
        return knldCapPhong;
    }

    public void setKnldCapPhong(String knldCapPhong) {
        this.knldCapPhong = knldCapPhong;
    }

    public String getKnldCapVu() {
        return knldCapVu;
    }

    public void setKnldCapVu(String knldCapVu) {
        this.knldCapVu = knldCapVu;
    }

    public String getKnldThuTruong() {
        return knldThuTruong;
    }

    public void setKnldThuTruong(String knldThuTruong) {
        this.knldThuTruong = knldThuTruong;
    }

    public String getQpan() {
        return qpan;
    }

    public void setQpan(String qpan) {
        this.qpan = qpan;
    }

    public String getNgoaiNgu() {
        return ngoaiNgu;
    }

    public void setNgoaiNgu(String ngoaiNgu) {
        this.ngoaiNgu = ngoaiNgu;
    }

    public String getTinHoc() {
        return tinHoc;
    }

    public void setTinHoc(String tinHoc) {
        this.tinHoc = tinHoc;
    }

    public String getCdnnHang1() {
        return cdnnHang1;
    }

    public void setCdnnHang1(String cdnnHang1) {
        this.cdnnHang1 = cdnnHang1;
    }

    public String getCdnnHang2() {
        return cdnnHang2;
    }

    public void setCdnnHang2(String cdnnHang2) {
        this.cdnnHang2 = cdnnHang2;
    }

    public String getCdnnHang3() {
        return cdnnHang3;
    }

    public void setCdnnHang3(String cdnnHang3) {
        this.cdnnHang3 = cdnnHang3;
    }

    public String getCdnnHang4() {
        return cdnnHang4;
    }

    public void setCdnnHang4(String cdnnHang4) {
        this.cdnnHang4 = cdnnHang4;
    }

    public String getCvqlCapPhong() {
        return cvqlCapPhong;
    }

    public void setCvqlCapPhong(String cvqlCapPhong) {
        this.cvqlCapPhong = cvqlCapPhong;
    }

    public String getCvqlCapVu() {
        return cvqlCapVu;
    }

    public void setCvqlCapVu(String cvqlCapVu) {
        this.cvqlCapVu = cvqlCapVu;
    }

    public String getBdbb() {
        return bdbb;
    }

    public void setBdbb(String bdbb) {
        this.bdbb = bdbb;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getLearnFromDate() {
        return learnFromDate;
    }

    public void setLearnFromDate(Date learnFromDate) {
        this.learnFromDate = learnFromDate;
    }

    public Date getLearnToDate() {
        return learnToDate;
    }

    public void setLearnToDate(Date learnToDate) {
        this.learnToDate = learnToDate;
    }

}
