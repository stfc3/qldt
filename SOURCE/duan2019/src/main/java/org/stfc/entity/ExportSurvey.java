/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.entity;

/**
 *
 * @author dmin
 */
public class ExportSurvey {

    private String positionType;
    private String positionName;
    private Long llctCaoCap;
    private Long llctTrungCap;
    private Long llctDangVienMoi;
    private Long llctKetNap;
    private Long qlnnChuyenVienCaoCap;
    private Long qlnnChuyenVienChinh;
    private Long qlnnChuyenVien;
    private Long qlnnCanSu;
    private Long cmTienSi;
    private Long cmThacSi;
    private Long cmDaiHoc;
    private Long cmCaoDang;
    private Long cmTrungCap;
    private Long cmSoCap;
    private Long ktknChuyenNganh;
    private Long ktknLamViec;
    private Long knldCapPhong;
    private Long knldCapVu;
    private Long knldThuTruong;
    private Long qpan;
    private Long ngoaiNgu;
    private Long tinHoc;
    private Long tong;
    private Integer danToc;
    private Long nu;
    
    
	public ExportSurvey(String positionType, String positionName, Long llctCaoCap, Long llctTrungCap,
			Long llctDangVienMoi, Long llctKetNap, Long qlnnChuyenVienCaoCap, Long qlnnChuyenVienChinh,
			Long qlnnChuyenVien, Long qlnnCanSu, Long cmTienSi, Long cmThacSi, Long cmDaiHoc, Long cmCaoDang,
			Long cmTrungCap, Long cmSoCap, Long ktknChuyenNganh, Long ktknLamViec, Long knldCapPhong, Long knldCapVu,
			Long knldThuTruong, Long qpan, Long ngoaiNgu, Long tinHoc, Long tong, Integer danToc, Long nu) {
		super();
		this.positionType = positionType;
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
		this.tong = tong;
		this.danToc = danToc;
		this.nu = nu;
	}
	public String getPositionType() {
		return positionType;
	}
	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Long getLlctCaoCap() {
		return llctCaoCap;
	}
	public void setLlctCaoCap(Long llctCaoCap) {
		this.llctCaoCap = llctCaoCap;
	}
	public Long getLlctTrungCap() {
		return llctTrungCap;
	}
	public void setLlctTrungCap(Long llctTrungCap) {
		this.llctTrungCap = llctTrungCap;
	}
	public Long getLlctDangVienMoi() {
		return llctDangVienMoi;
	}
	public void setLlctDangVienMoi(Long llctDangVienMoi) {
		this.llctDangVienMoi = llctDangVienMoi;
	}
	public Long getLlctKetNap() {
		return llctKetNap;
	}
	public void setLlctKetNap(Long llctKetNap) {
		this.llctKetNap = llctKetNap;
	}
	public Long getQlnnChuyenVienCaoCap() {
		return qlnnChuyenVienCaoCap;
	}
	public void setQlnnChuyenVienCaoCap(Long qlnnChuyenVienCaoCap) {
		this.qlnnChuyenVienCaoCap = qlnnChuyenVienCaoCap;
	}
	public Long getQlnnChuyenVienChinh() {
		return qlnnChuyenVienChinh;
	}
	public void setQlnnChuyenVienChinh(Long qlnnChuyenVienChinh) {
		this.qlnnChuyenVienChinh = qlnnChuyenVienChinh;
	}
	public Long getQlnnChuyenVien() {
		return qlnnChuyenVien;
	}
	public void setQlnnChuyenVien(Long qlnnChuyenVien) {
		this.qlnnChuyenVien = qlnnChuyenVien;
	}
	public Long getQlnnCanSu() {
		return qlnnCanSu;
	}
	public void setQlnnCanSu(Long qlnnCanSu) {
		this.qlnnCanSu = qlnnCanSu;
	}
	public Long getCmTienSi() {
		return cmTienSi;
	}
	public void setCmTienSi(Long cmTienSi) {
		this.cmTienSi = cmTienSi;
	}
	public Long getCmThacSi() {
		return cmThacSi;
	}
	public void setCmThacSi(Long cmThacSi) {
		this.cmThacSi = cmThacSi;
	}
	public Long getCmDaiHoc() {
		return cmDaiHoc;
	}
	public void setCmDaiHoc(Long cmDaiHoc) {
		this.cmDaiHoc = cmDaiHoc;
	}
	public Long getCmCaoDang() {
		return cmCaoDang;
	}
	public void setCmCaoDang(Long cmCaoDang) {
		this.cmCaoDang = cmCaoDang;
	}
	public Long getCmTrungCap() {
		return cmTrungCap;
	}
	public void setCmTrungCap(Long cmTrungCap) {
		this.cmTrungCap = cmTrungCap;
	}
	public Long getCmSoCap() {
		return cmSoCap;
	}
	public void setCmSoCap(Long cmSoCap) {
		this.cmSoCap = cmSoCap;
	}
	public Long getKtknChuyenNganh() {
		return ktknChuyenNganh;
	}
	public void setKtknChuyenNganh(Long ktknChuyenNganh) {
		this.ktknChuyenNganh = ktknChuyenNganh;
	}
	public Long getKtknLamViec() {
		return ktknLamViec;
	}
	public void setKtknLamViec(Long ktknLamViec) {
		this.ktknLamViec = ktknLamViec;
	}
	public Long getKnldCapPhong() {
		return knldCapPhong;
	}
	public void setKnldCapPhong(Long knldCapPhong) {
		this.knldCapPhong = knldCapPhong;
	}
	public Long getKnldCapVu() {
		return knldCapVu;
	}
	public void setKnldCapVu(Long knldCapVu) {
		this.knldCapVu = knldCapVu;
	}
	public Long getKnldThuTruong() {
		return knldThuTruong;
	}
	public void setKnldThuTruong(Long knldThuTruong) {
		this.knldThuTruong = knldThuTruong;
	}
	public Long getQpan() {
		return qpan;
	}
	public void setQpan(Long qpan) {
		this.qpan = qpan;
	}
	public Long getNgoaiNgu() {
		return ngoaiNgu;
	}
	public void setNgoaiNgu(Long ngoaiNgu) {
		this.ngoaiNgu = ngoaiNgu;
	}
	public Long getTinHoc() {
		return tinHoc;
	}
	public void setTinHoc(Long tinHoc) {
		this.tinHoc = tinHoc;
	}
	public Long getTong() {
		return tong;
	}
	public void setTong(Long tong) {
		this.tong = tong;
	}
	public Integer getDanToc() {
		return danToc;
	}
	public void setDanToc(Integer danToc) {
		this.danToc = danToc;
	}
	public Long getNu() {
		return nu;
	}
	public void setNu(Long nu) {
		this.nu = nu;
	}
    

}