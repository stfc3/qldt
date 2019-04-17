/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dmin
 */
@Entity
@Table(name = "survey_results")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyResults.findAll", query = "SELECT s FROM SurveyResults s")})
public class SurveyResults implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "survey_result_id")
    private Long surveyResultId;
    @Column(name = "survey_id")
    private BigInteger surveyId;
    @Column(name = "question_id")
    private BigInteger questionId;
    @Column(name = "officer_id")
    private BigInteger officerId;
    @Size(max = 1000)
    @Column(name = "answer")
    private String answer;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public SurveyResults() {
    }

    public SurveyResults(Long surveyResultId) {
        this.surveyResultId = surveyResultId;
    }

    public Long getSurveyResultId() {
        return surveyResultId;
    }

    public void setSurveyResultId(Long surveyResultId) {
        this.surveyResultId = surveyResultId;
    }

    public BigInteger getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(BigInteger surveyId) {
        this.surveyId = surveyId;
    }

    public BigInteger getQuestionId() {
        return questionId;
    }

    public void setQuestionId(BigInteger questionId) {
        this.questionId = questionId;
    }

    public BigInteger getOfficerId() {
        return officerId;
    }

    public void setOfficerId(BigInteger officerId) {
        this.officerId = officerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (surveyResultId != null ? surveyResultId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyResults)) {
            return false;
        }
        SurveyResults other = (SurveyResults) object;
        if ((this.surveyResultId == null && other.surveyResultId != null) || (this.surveyResultId != null && !this.surveyResultId.equals(other.surveyResultId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.stfc.dto.SurveyResults[ surveyResultId=" + surveyResultId + " ]";
    }
    
}
