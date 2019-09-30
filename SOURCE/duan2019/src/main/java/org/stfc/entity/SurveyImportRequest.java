/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.entity;

import java.util.Date;

import org.stfc.utils.Comparator;
import org.stfc.utils.Constants;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * @author dmin
 */
public class SurveyImportRequest {

    // Tieu de trong file bieu mau excel
    private String surveyTitle;
    //ho ten
    private String fullName;
    //ho ten
    private String gender;
    //so dien thoai
    private String mobile;
    //email
    private String email;
    //Loại Chuc vu
    private String positionType;
    //Chuc vu
    private String positionName;
    //Ghep tieu de cac khoa hoc
    private String questionContent;
    //ma khoa hoc
    private String questionCode;
    //cau tra loi cho tung mon hoc cua hoc vien
    private String answer;
    //thoi gian co the hoc
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT.DD_MM_YYYY_HH_MM_SS, timezone = Constants.DATE_FORMAT.TIMEZONE_HCM)
    private Date learnFromDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT.DD_MM_YYYY_HH_MM_SS, timezone = Constants.DATE_FORMAT.TIMEZONE_HCM)
    private Date learnToDate;

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public String getFirstName() {
        if (!Comparator.isEqualNullOrEmpty(fullName)) {
            String[] temp = fullName.split(" ");
            return temp[temp.length - 1].trim();
        }
        return "";
    }

    public String getLastName() {
        if (!Comparator.isEqualNullOrEmpty(fullName)) {
            String[] temp = fullName.split(" ");
            if (temp.length == 1) {
                return fullName;
            } else {
                String lastName = "";
                for (int i = 0; i < temp.length - 1; i++) {
                    lastName += temp[i] + " ";
                }
                return lastName.trim();
            }
        }
        return "";
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

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

}
