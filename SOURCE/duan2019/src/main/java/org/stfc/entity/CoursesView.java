/**
 *
 */
package org.stfc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.stfc.utils.Constants;

/**
 * @author dongdv
 *
 */
public class CoursesView {

    private String courseName;
    private int status;
    private long totalOfficer;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT.DD_MM_YYYY_HH_MM_SS, timezone = Constants.DATE_FORMAT.TIMEZONE_HCM)
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT.DD_MM_YYYY_HH_MM_SS, timezone = Constants.DATE_FORMAT.TIMEZONE_HCM)
    private Date endDate;
    private String lecturerName;
    private Long surveyId;
    private Long questionId;

    public CoursesView(Long surveyId, Long questionId, String courseName, int status, long totalOfficer, Date startDate, Date endDate) {
        this.courseName = courseName;
        this.status = status;
        this.totalOfficer = totalOfficer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.surveyId = surveyId;
        this.questionId = questionId;
    }

    public CoursesView(String courseName, int status, Date startDate, Date endDate, String lecturerName) {
        this.courseName = courseName;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lecturerName = lecturerName;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTotalOfficer() {
        return totalOfficer;
    }

    public void setTotalOfficer(long totalOfficer) {
        this.totalOfficer = totalOfficer;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

}
