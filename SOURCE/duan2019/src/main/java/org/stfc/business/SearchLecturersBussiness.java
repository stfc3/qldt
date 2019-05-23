/**
 * 
 */
package org.stfc.business;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
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
import org.stfc.specification.SearchOperation;
import org.stfc.utils.Comparator;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;

/**
 * @author viettx
 *
 */
@Service
public class SearchLecturersBussiness implements Business {
	private static final Logger logger = LoggerFactory.getLogger(SearchLecturersBussiness.class);

	FormatMessage formatMessage;
	@Autowired
	LecturersRepository repository;
	@Autowired
	LecturersCustomerRepositoryImp imp;

	CacheInfo cacheInfo;

	/**
	 * @param formatMessage the formatMessage to set
	 */
	public void setFormatMessage(FormatMessage formatMessage) {
		this.formatMessage = formatMessage;
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
			logger.debug("Request {}", gson.toJson(req));
			List<Lecturers> listAllData = null;
			if (req == null) {
				listAllData = imp.onSearch(null);
			} else {

				if (!Comparator.isEqualNull(req.getQuery())) {
					STFCSpecification<Lecturers> stfcSpecification = new STFCSpecification<Lecturers>();
					String query = stfcSpecification.getFieldNames(Lecturers.class, req.getQuery());
					Specification<Lecturers> spec = stfcSpecification
							.getSpecification(SearchOperation.OR_PREDICATE_FLAG, query);
					listAllData = repository.findAll(spec);
				} else {
					listAllData = imp.onSearch(req);
				}

			}
			LecturersResponse lecturersResponse = new LecturersResponse();
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
