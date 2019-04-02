/**
 * 
 */
package org.stfc.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stfc.dto.Users;
import org.stfc.message.UsersRequest;
import org.stfc.message.UsersResponse;
import org.stfc.message.BaseResponse;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;
import org.stfc.repository.UsersRepository;

/**
 * @author dongdv
 *
 */
public class UsersBusiness implements Business {
	private static final Logger logger = LoggerFactory.getLogger(UsersBusiness.class);
	FormatMessage formatMessage;
	UsersRepository repository;

	/**
	 * @param repository the repository to set
	 */
	public void setRepository(UsersRepository repository) {
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
		long start = System.currentTimeMillis();
		String lang = "vi";
		String TAG = "getAllUsers";
		try {
			UsersRequest req = gson.fromJson(request, UsersRequest.class);
			if (req == null) {
				throw new BusinessException(Contants.ERROR_INVALID_FORMAT);
			}
			UsersResponse usersResponse = new UsersResponse();
			/**
			 * Lay all danh sach user tu database
			 */
			List<Users> listAllData = repository.findAll();
			usersResponse.setListUsers(listAllData);
			logger.debug("Data response {}", gson.toJson(usersResponse));
			res = BaseResponse.parse(Contants.SUCCESS, formatMessage, lang);
			res.setData(usersResponse);
		} catch (BusinessException be) {
			res = BaseResponse.parse(be.getMessage(), formatMessage, lang);
		} catch (Exception e) {
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
