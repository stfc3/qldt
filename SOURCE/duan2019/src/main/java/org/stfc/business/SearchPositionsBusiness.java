/**
 * 
 */
package org.stfc.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stfc.dto.Positions;
import org.stfc.message.BaseResponse;
import org.stfc.repository.PositionsRepository;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;

/**
 * @author viettx
 *
 */
public class SearchPositionsBusiness implements Business {
	private static final Logger logger = LoggerFactory.getLogger(SearchPositionsBusiness.class);
	FormatMessage formatMessage;
	PositionsRepository repository;

	/**
	 * 
	 */
	public SearchPositionsBusiness() {
		super();
	}

	/**
	 * @param formatMessage
	 * @param repository
	 */
	public SearchPositionsBusiness(FormatMessage formatMessage, PositionsRepository repository) {
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
		String TAG = "getAllPositions";
		try {

			/**
			 * Lay danh sach vi tri lam viec, chuc vu trong bo dang o trang thai hoat dong
			 */
			List<Positions> listAllData = repository.findAllPositions();

			/**
			 * Gan gia tri tra ve cho client
			 */
//			PositionsResponse response = new PositionsResponse();
//			response.setListPos(listAllData);
			logger.debug("Data response {}", gson.toJson(listAllData));
			res = BaseResponse.parse(Contants.SUCCESS, formatMessage, lang);
			res.setData(listAllData);
			res.setTotal(listAllData.size());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// TODO: handle exception
		} finally {
			logger.info(Contants.LOG_FORMAT_TIME, TAG, "getAllPositions proc  ",
					Long.toString(System.currentTimeMillis() - start));
		}
		return res;
	}

}
