/**
 * 
 */
package org.stfc.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.stfc.business.BusinessException;
import org.stfc.dto.Certificate;
import org.stfc.message.BaseResponse;
import org.stfc.repository.CertificateRepository;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @category thong tin cac chung chi
 * @author viettx
 *
 */
@RestController
public class CertificateController {
	private static final Logger logger = LoggerFactory.getLogger(CertificateController.class);
	@Autowired
	CertificateRepository repository;
	@Autowired
	FormatMessage formatMessage;

	/**
	 * @category Lay danh sach cac chung chi
	 * @param lecturerId
	 * @return
	 */
	@GetMapping(path = "/certificates", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String getCertificate(@PathVariable Long certificateId) {
		String lang = "vi";
		logger.debug("Get certificate by {}", certificateId);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, lang);
		try {
			if (certificateId == null) {
				throw new BusinessException(Contants.ERROR_ID_EMPTY);
			}
			List<Certificate> listData = repository.findByActive();
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage);
			response.setData(listData);

		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			response = BaseResponse.parse(e.getMessage(), formatMessage);
			// TODO: handle exception
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// TODO: handle exception
		}
		return gson.toJson(response);
	}

	/**
	 * @category them moi, chinh sua thong tin chung chi
	 * @param Certificate
	 * @return
	 */
	@PostMapping(path = "/certificate/save", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String saveCertificate(@RequestBody Certificate certificate) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		logger.debug("Body request: {}", gson.toJson(certificate));
		String lang = "vi";
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, lang);

		try {
			if (certificate == null) {
				throw new BusinessException(Contants.ERROR_INVALID_FORMAT);
			}
			if (certificate.getId() == null) {
				certificate.setCreateDate(new Date());
			} else {
				certificate.setModifiedDate(new Date());
			}
			repository.save(certificate);
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage, lang);
		} catch (BusinessException be) {
			response = BaseResponse.parse(be.getMessage(), formatMessage, lang);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	/**
	 * @category xoa thong tin chung chi
	 * @param coursesId
	 * @return
	 */
	@DeleteMapping(value = { "/certificate/delete/{certificateId}" }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String delete(@PathVariable Long certificateId) {
		String lang = "vi";
		logger.debug("Delete certificateId: {}", certificateId);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, lang);

		try {
			/**
			 * Khong the xoa khi id null, thong bao chung loi 02
			 */
			if (certificateId == null) {
				throw new BusinessException(Contants.ERROR_ID_EMPTY);
			}
			repository.deleteById(certificateId);
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage, lang);
		} catch (BusinessException be) {
			response = BaseResponse.parse(be.getMessage(), formatMessage, lang);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

}
