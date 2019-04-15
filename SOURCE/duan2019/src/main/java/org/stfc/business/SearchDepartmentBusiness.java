/**
 * 
 */
package org.stfc.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stfc.dto.Departments;
import org.stfc.message.BaseResponse;
import org.stfc.message.DepartmentResponse;
import org.stfc.repository.DepartmentsRepository;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;

/**
 * @author viettx
 *
 */
public class SearchDepartmentBusiness implements Business {
	private static final Logger logger = LoggerFactory.getLogger(SearchDepartmentBusiness.class);
	FormatMessage formatMessage;
	DepartmentsRepository repository;

	/**
	 * 
	 */
	public SearchDepartmentBusiness() {
		super();
	}

	/**
	 * @param formatMessage
	 * @param repository
	 */
	public SearchDepartmentBusiness(FormatMessage formatMessage, DepartmentsRepository repository) {
		super();
		this.formatMessage = formatMessage;
		this.repository = repository;
	}

	/**
	 * @param formatMessage the formatMessage to set
	 */
	public void setFormatMessage(FormatMessage formatMessage) {
		this.formatMessage = formatMessage;
	}

	@Override
	public BaseResponse process(String request, Gson gson) {
		// TODO Auto-generated method stub
		BaseResponse res = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);

		return res;
	}

	/**
	 * Ham su dung voi nhung api co metho la GET
	 */
	@Override
	public BaseResponse process(Gson gson) {
		BaseResponse res = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		long start = System.currentTimeMillis();
		String lang = "vi";
		String TAG = "onSearchLecturers";
		try {

			/**
			 * Lay danh sach phong ban trong bo dang o trang thai hoat dong
			 */
			List<Departments> listAllData = repository.findAllDepartmentsActive();

			/**
			 * Gan gia tri tra ve cho client
			 */
			DepartmentResponse response = new DepartmentResponse();
			response.setListDept(listAllData);
			logger.debug("Data response {}", gson.toJson(response));
			res = BaseResponse.parse(Contants.SUCCESS, formatMessage, lang);
			res.setData(response);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// TODO: handle exception
		} finally {
			logger.info(Contants.LOG_FORMAT_TIME, TAG, "handlegetAllAnswers proc  ",
					Long.toString(System.currentTimeMillis() - start));
		}
		return res;
	}

}
