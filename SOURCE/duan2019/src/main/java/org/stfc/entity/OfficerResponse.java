/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.entity;

import java.util.List;
import org.stfc.dto.CertificateOfficers;
import org.stfc.dto.Courses;
import org.stfc.dto.Officers;

/**
 *
 * @author dmin
 */
public class OfficerResponse {

    private Officers officer;
    private List<CertificateOfficers> officerCertificates;
    private List<Courses> courses;

    public Officers getOfficer() {
        return officer;
    }

    public void setOfficer(Officers officer) {
        this.officer = officer;
    }

    public List<CertificateOfficers> getOfficerCertificates() {
        return officerCertificates;
    }

    public void setOfficerCertificates(List<CertificateOfficers> officerCertificates) {
        this.officerCertificates = officerCertificates;
    }

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }
    

}
