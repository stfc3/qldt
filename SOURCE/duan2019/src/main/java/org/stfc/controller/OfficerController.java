/**
 *
 */
package org.stfc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.stfc.message.BaseResponse;
import org.stfc.utils.Constants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.stfc.dto.CertificateOfficers;
import org.stfc.dto.Officers;
import org.stfc.entity.OfficerResponse;
import org.stfc.repository.impl.OfficersRepositoryImpl;
import org.stfc.utils.Comparator;

/**
 * @author dongdv
 *
 */
@RestController
public class OfficerController {

	private static final Logger logger = LoggerFactory.getLogger(OfficerController.class);
	@Autowired
	OfficersRepositoryImpl officersRepositoryImpl;
	@Autowired
	FormatMessage formatMessage;

	@RequestMapping(value = Constants.PATH.API_OFFICER, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getOfficerByUsername(@RequestParam String username) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		logger.debug("Body request: {}", gson.toJson(username));
		String lang = "vi";
		BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
		try {
			Officers officer = officersRepositoryImpl.findOfficerByUsername(username);
			OfficerResponse officerResponse = new OfficerResponse();
			if (Comparator.isEqualNull(officer)) {
				response = BaseResponse.parse(Constants.ERROR_DATA_EMPTY, formatMessage, lang);
			} else {
				response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
				officerResponse.setOfficer(officer);
				List<Object[]> listData = officersRepositoryImpl.findCertificatesByOfficer(officer.getOfficerId());

				officerResponse.setOfficerCertificates(getCertificateOfficers(listData));
				officerResponse.setCourses(officersRepositoryImpl.findCoursesByOfficer(officer.getOfficerId()));
				response.setData(officerResponse);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	private List<CertificateOfficers> getCertificateOfficers(List<Object[]> listData) {
		List<CertificateOfficers> certificateOfficers = new ArrayList<CertificateOfficers>();
		if (!Comparator.isEqualNullOrEmpty(listData)) {
			for (Object[] obj : listData) {
				CertificateOfficers officers = new CertificateOfficers();
				if (!Comparator.isEqualNull(obj[0])) {
					officers.setId(Long.valueOf(String.valueOf(obj[0])));
				}
				if (!Comparator.isEqualNull(obj[1])) {
					officers.setOfficer(Long.valueOf(String.valueOf(obj[1])));
				}
				if (!Comparator.isEqualNull(obj[2])) {
					officers.setNumberCert(String.valueOf(obj[2]));
				}
				if (!Comparator.isEqualNull(obj[3])) {
					officers.setCertificate(Long.valueOf(String.valueOf(obj[3])));
				}
				if (!Comparator.isEqualNull(obj[4])) {
					officers.setDateCert((Date) obj[4]);
				}
				if (!Comparator.isEqualNull(obj[5])) {
					officers.setPlaceCert(String.valueOf(obj[5]));
				}
				if (!Comparator.isEqualNull(obj[6])) {
					officers.setStatus(Integer.valueOf(String.valueOf(obj[6])));
				}
				if (!Comparator.isEqualNull(obj[7])) {
					officers.setCreateDate((Date) obj[7]);
				}
				if (!Comparator.isEqualNull(obj[8])) {
					officers.setModifiedDate((Date) obj[8]);
				}
				if (!Comparator.isEqualNull(obj[9])) {
					officers.setCertificateName(String.valueOf(obj[9]));
				}
				certificateOfficers.add(officers);
			}
		}
		return certificateOfficers;
	}
}
