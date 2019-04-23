/**
 * 
 */
package org.stfc.business;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.stfc.cache.CacheInfo;
import org.stfc.dto.Departments;
import org.stfc.dto.Lecturers;
import org.stfc.dto.Positions;
import org.stfc.entity.LecturersEntity;
import org.stfc.message.BaseResponse;
import org.stfc.message.LecturersRequest;
import org.stfc.message.LecturersResponse;
import org.stfc.repository.LecturersRepository;
import org.stfc.repository.impl.LecturersCustomerRepositoryImp;
import org.stfc.specification.STFCSpecification;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;

/**
 * @author viettx
 *
 */

public class SearchLecturersBussiness implements Business {
	private static final Logger logger = LoggerFactory.getLogger(SearchLecturersBussiness.class);

	FormatMessage formatMessage;

	LecturersRepository repository;

	LecturersCustomerRepositoryImp imp;

	CacheInfo cacheInfo;

	/**
	 * @param formatMessage the formatMessage to set
	 */
	public void setFormatMessage(FormatMessage formatMessage) {
		this.formatMessage = formatMessage;
	}

	/**
	 * @param repository the repository to set
	 */
	public void setRepository(LecturersRepository repository) {
		this.repository = repository;
	}

	/**
	 * @param imp the imp to set
	 */
	public void setImp(LecturersCustomerRepositoryImp imp) {
		this.imp = imp;
	}

	/**
	 * @param cacheInfo the cacheInfo to set
	 */
	public void setCacheInfo(CacheInfo cacheInfo) {
		this.cacheInfo = cacheInfo;
	}

	@Override
	public BaseResponse process(String request, Gson gson) {
		// TODO Auto-generated method stub
		BaseResponse res = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		long start = System.currentTimeMillis();
		String lang = "vi";
		String TAG = "onSearchLecturers";
		try {
			LecturersRequest req = gson.fromJson(request, LecturersRequest.class);
			if (req == null) {
				throw new BusinessException(Contants.ERROR_INVALID_FORMAT);
			}
			LecturersResponse lecturersResponse = new LecturersResponse();
			STFCSpecification<Lecturers> stfcSpecification = new STFCSpecification<Lecturers>();
//			Field[] fields = Lecturers.getDeclaredFields();
			String query = stfcSpecification.getFieldNames(Lecturers.class, req.getQuery());
			Specification<Lecturers> spec = stfcSpecification.getSpecification(query);
			List<Lecturers> listAllData = repository.findAll(spec);
//			System.out.println(listData.size());
//			List<Lecturers> listAllData = imp.onSearch(req.getFullName(), req.getGender(), req.getPhone(),
//					req.getEmail(), req.getDepId(), req.getPosId(), req.getStauts());
			List<LecturersEntity> listData = new ArrayList<>();

			if (listAllData != null && !listAllData.isEmpty()) {
				for (Lecturers lecturers : listAllData) {
					LecturersEntity entity = new LecturersEntity();
					entity.setId(lecturers.getId());
					entity.setFistName(lecturers.getFistName());
					entity.setLastName(lecturers.getLastName());
					entity.setGender(lecturers.getGender());
					entity.setMobile(lecturers.getMobile());
					entity.setEmail(lecturers.getEmail());
					Departments department = cacheInfo.getDepartments().get(lecturers.getDeptId());
					if (department != null) {
						entity.setDepartment(department.getName());
					}
					Positions positions = cacheInfo.getPositions().get(lecturers.getPosiId());
					if (positions != null) {
						entity.setPosition(positions.getPosiName());
					}
					entity.setStatus(formatMessage.getStatusName(lecturers.getStatus(), lang));
					listData.add(entity);
				}
			}
//			lecturersResponse.setListLecturers(listData);

			logger.debug("Data response {}", gson.toJson(lecturersResponse));
			res = BaseResponse.parse(Contants.SUCCESS, formatMessage, lang);
			res.setTotal(listData.size());
			res.setData(listData);
		} catch (BusinessException be) {
			logger.error(be.getMessage(), be);
			res = BaseResponse.parse(be.getMessage(), formatMessage, lang);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// TODO: handle exception
		} finally {
			logger.info(Contants.LOG_FORMAT_TIME, TAG, "handlegetAllAnswers proc  ",
					Long.toString(System.currentTimeMillis() - start));
		}
		return res;
	}

	@Override
	public BaseResponse process(Gson gson) {
		// TODO Auto-generated method stub
		return null;
	}

}
