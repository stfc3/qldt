/**
 * 
 */
package org.stfc.business;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stfc.dto.Lecturers;
import org.stfc.message.BaseResponse;
import org.stfc.repository.LecturersRepository;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;

/**
 * @author viettx
 *
 */
public class SaveLecturerBusiness implements Business {
	private static final Logger logger = LoggerFactory.getLogger(SaveLecturerBusiness.class);
	FormatMessage formatMessage;
	LecturersRepository repository;

	/**
	 * @param repository the repository to set
	 */
	public void setRepository(LecturersRepository repository) {
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
		String TAG = "addLecturer";
		try {
			Lecturers req = gson.fromJson(request, Lecturers.class);
			if (req == null) {
				throw new BusinessException(Contants.ERROR_INVALID_FORMAT);
			}
			if (req.getId() == null) {
				req.setCreateDate(new Date());
			} else {
				req.setModifiedDate(new Date());
			}
			repository.save(req);
			res = BaseResponse.parse(Contants.SUCCESS, formatMessage, lang);
//			res.setData(answerRepository);
		} catch (BusinessException be) {
			res = BaseResponse.parse(be.getMessage(), formatMessage, lang);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			logger.info(Contants.LOG_FORMAT_TIME, TAG, "handleaddLecturer proc  ",
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
