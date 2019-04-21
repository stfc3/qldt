/**
 * 
 */
package org.stfc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.stfc.business.BusinessException;
import org.stfc.business.SearchPositionsBusiness;
import org.stfc.dto.Certificate;
import org.stfc.dto.CertificatePosition;
import org.stfc.dto.Positions;
import org.stfc.entity.CertificatePositionEntity;
import org.stfc.message.BaseResponse;
import org.stfc.message.CertPositionResponse;
import org.stfc.repository.CertificatePositionRepository;
import org.stfc.repository.CertificateRepository;
import org.stfc.repository.PositionsRepository;
import org.stfc.utils.Comparator;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @category Quan ly vi tri, chuc vu
 * @author viettx
 *
 */
@RestController
public class PositionsController {
	private static final Logger logger = LoggerFactory.getLogger(PositionsController.class);
	@Autowired
	PositionsRepository repository;
	@Autowired
	FormatMessage formatMessage;
	@Autowired
	CertificatePositionRepository certificatePositionRepository;
	@Autowired
	CertificateRepository certRepository;

	/**
	 * @category Lay thong tin cac vi tri hien co
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.GET }, value = { "/positions" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String getPositions() {
		logger.debug("Methot GET ");
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		try {
			SearchPositionsBusiness bussiness = new SearchPositionsBusiness(formatMessage, repository);
			response = bussiness.process(gson);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	@RequestMapping(method = { RequestMethod.GET }, value = { "/positions/{positionId}" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String findPositions(@PathVariable Long positionId) {
		logger.debug("Methot GET ");
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		try {
			if (Comparator.isEqualNull(positionId)) {
				throw new BusinessException(Contants.ERROR_ID_EMPTY);
			}
			Positions positions = repository.findByPositionId(positionId);
			if (Comparator.isEqualNull(positions)) {
				throw new BusinessException(Contants.ERROR_DATA_EMPTY);
			}
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage);
			response.setData(positions);
			response.setTotal(1);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	/**
	 * @category Them moi 1 vi tri, chuc danh
	 * @param httpEntity
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST }, value = { "/positions/save" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String savePositions(HttpEntity<String> httpEntity) {
		String lang = "vi";
		String body = httpEntity.getBody();
		logger.debug("Body request: {}", body);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, lang);

		try {
			Positions positions = gson.fromJson(body, Positions.class);
			if (positions == null) {
				throw new BusinessException(Contants.ERROR_INVALID_FORMAT);
			}
			if (positions.getId() == null) {
				positions.setCreateDate(new Date());
			} else {
				positions.setModifiedDate(new Date());
			}
			repository.save(positions);
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage, lang);
		} catch (BusinessException be) {
			logger.error(be.getMessage(), be);
			response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	@RequestMapping(method = { RequestMethod.POST }, value = { "/positions/saves" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String savesPositions(@RequestBody List<Positions> positions) {
		String lang = "vi";
//		String body = httpEntity.getBody();

		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		logger.debug("Body request: {}", gson.toJson(positions));
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, lang);

		try {
//			List<Positions> positions = gson.fromJson(body, List.class);
			if (positions == null) {
				throw new BusinessException(Contants.ERROR_INVALID_FORMAT);
			}
			for (Positions pos : positions) {
				if (pos.getId() == null) {
					pos.setCreateDate(new Date());
				} else {
					pos.setModifiedDate(new Date());
				}
			}
			repository.saveAll(positions);
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage, lang);
		} catch (BusinessException be) {
			logger.error(be.getMessage(), be);
			response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	/**
	 * @category Xoa thong tin 1 vi tri hien co
	 * @param positionsId
	 * @return
	 */
	@DeleteMapping(value = { "/positions/delete/{positionsId}" })
	public String deletePositions(@PathVariable Long positionsId) {
		String lang = "vi";
		logger.debug("Delete positions: {}", positionsId);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, lang);
		try {
			if (positionsId == null) {
				throw new BusinessException(Contants.ERROR_ID_EMPTY);
			}
			repository.deleteById(positionsId);
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage, lang);
		} catch (BusinessException be) {
			logger.error(be.getMessage(), be);
			response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

	/**
	 * @category Lay thong tin chung chi theo vi tri
	 * @param positionsId
	 * @return
	 */

	@GetMapping(path = "/positions/certificate/{positionsId}", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String getCertificate(@PathVariable Long positionsId) {
		String lang = "vi";
		logger.debug("Get positions by {}", positionsId);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, lang);
		try {
			if (positionsId == null) {
				throw new BusinessException(Contants.ERROR_ID_EMPTY);
			}
			/**
			 * Thong tin vi tri
			 */
			Positions positions = repository.getOne(positionsId);
			/**
			 * Hien khong co vi tri nao theo id
			 */
			if (positions == null) {
				throw new BusinessException(Contants.ERROR_POSITION_EMPTY);
			}
			/**
			 * Danh sach cac chung chi theo vi tri, chuc vu
			 */
			List<CertificatePosition> listCertPosition = certificatePositionRepository.findByPositions(positionsId);
			/**
			 * Danh sach thong tin chung chi
			 */
			Map<Long, Certificate> mapCert = new HashMap<>();
			List<Certificate> listCert = certRepository.findByActive();
			/**
			 * Chuyen doi tu list sang map
			 */
			if (listCert != null && !listCert.isEmpty()) {
				for (Certificate cert : listCert) {
					mapCert.put(cert.getId(), cert);
				}
			}

			List<CertificatePositionEntity> listData = new ArrayList<>();
			if (listCertPosition != null && !listCertPosition.isEmpty()) {
				for (CertificatePosition certPosi : listCertPosition) {
					CertificatePositionEntity entity = new CertificatePositionEntity();
					/**
					 * Thong tin chung chi
					 */
					Certificate cert = mapCert.get(certPosi.getCertificate());
					/**
					 * Thong tin chung chi khong ton tai
					 */
					if (cert == null) {
						throw new BusinessException(Contants.ERROR_CERTIFICATE_EMPTY);
					}
					entity.setCertificateName(cert.getCertificateName());
					entity.setCertificateType(cert.getType());
					listData.add(entity);
				}
			}
			CertPositionResponse certPositionResponse = new CertPositionResponse();
			certPositionResponse.setPositionId(positions.getId());
			certPositionResponse.setPositionName(positions.getPosiName());
			certPositionResponse.setListCertification(listData);
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage);
			response.setData(certPositionResponse);

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
	 * @category Them moi 1 thong tin vi tri theo chung chi
	 * @ @param httpEntity
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST }, value = { "/positions/certificate/save" }, headers = {
			"Accept=application/json" }, produces = { "text/plain;charset=UTF-8" })
	public String saveCertificatePositions(HttpEntity<String> httpEntity) {
		String lang = "vi";
		String body = httpEntity.getBody();
		logger.debug("Body request: {}", body);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		BaseResponse response = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, lang);

		try {
			CertificatePosition certPositions = gson.fromJson(body, CertificatePosition.class);
			if (certPositions == null) {
				throw new BusinessException(Contants.ERROR_INVALID_FORMAT);
			}
			certificatePositionRepository.save(certPositions);
			response = BaseResponse.parse(Contants.SUCCESS, formatMessage, lang);
		} catch (BusinessException be) {
			logger.error(be.getMessage(), be);
			response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return gson.toJson(response);
	}

}
