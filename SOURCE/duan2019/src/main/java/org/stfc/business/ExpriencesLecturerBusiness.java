/**
 * 
 */
package org.stfc.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stfc.dto.Experiences;
import org.stfc.message.BaseResponse;
import org.stfc.message.ObjectLecturerRequest;
import org.stfc.repository.ExperiencesRepository;
import org.stfc.repository.LecturersRepository;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;

/**
 * @author viett
 *
 */
public class ExpriencesLecturerBusiness implements Business {
	private static final Logger logger = LoggerFactory.getLogger(ExpriencesLecturerBusiness.class);
	FormatMessage formatMessage;
	LecturersRepository repository;
	ExperiencesRepository experiencesRepository;

	/**
	 * @param experiencesRepository the experiencesRepository to set
	 */
	public void setExperiencesRepository(ExperiencesRepository experiencesRepository) {
		this.experiencesRepository = experiencesRepository;
	}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.stfc.business.Business#process(java.lang.String,
	 * com.google.gson.Gson)
	 */
	@Override
	public BaseResponse process(String request, Gson gson) {
		BaseResponse res = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage);
		long start = System.currentTimeMillis();
		String lang = "vi";
		String TAG = "onSearchLecturers";
		try {
			ObjectLecturerRequest req = gson.fromJson(request, ObjectLecturerRequest.class);
			if (req == null) {
				throw new BusinessException(Contants.ERROR_INVALID_FORMAT);
			}
//			ExpriencesLecturerResponse response = new ExpriencesLecturerResponse();
//			Lecturers lecturers = repository.findLecturersById(req.getLecturerId());
//			if (lecturers != null) {
//				response.setLecturer(lecturers);
//			}
			List<Experiences> listExperiences = experiencesRepository.findByLecturerId(req.getLecturerId());
			if (listExperiences != null && !listExperiences.isEmpty()) {
				throw new BusinessException(Contants.ERROR_DATA_EMPTY);
			}

			res = BaseResponse.parse(Contants.SUCCESS, formatMessage, lang);
			res.setTotal(listExperiences.size());
			res.setData(listExperiences);

		} catch (BusinessException be) {
			logger.error(be.getMessage(), be);
			res = BaseResponse.parse(be.getMessage(), formatMessage, lang);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.info(Contants.LOG_FORMAT_TIME, TAG, "handlegetAllAnswers proc  ",
					Long.toString(System.currentTimeMillis() - start));
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.stfc.business.Business#process(com.google.gson.Gson)
	 */
	@Override
	public BaseResponse process(Gson gson) {
		// TODO Auto-generated method stub
		return null;
	}

}
