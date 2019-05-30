/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.entity;

import org.stfc.utils.Comparator;

/**
 *
 * @author dmin
 */
public class SurveyImportRequest {

    private String surveyTitle;
    private String fullName;
    private String mobile;
    private String email;
    private String positionName;
    private String questionContent;
    private String answer;

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

}
