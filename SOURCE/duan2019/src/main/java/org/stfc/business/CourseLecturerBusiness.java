/**
 * 
 */
package org.stfc.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stfc.dto.Courses;
import org.stfc.dto.Lecturers;
import org.stfc.message.BaseResponse;
import org.stfc.message.CoursesLecturerResponse;
import org.stfc.message.ObjectLecturerRequest;
import org.stfc.repository.CoursesRepository;
import org.stfc.repository.LecturersRepository;
import org.stfc.utils.Contants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;

/**
 * @author viettx
 * @version 1.0
 * @since
 *
 */
public class CourseLecturerBusiness implements Business {
	private static final Logger logger = LoggerFactory.getLogger(CourseLecturerBusiness.class);
	FormatMessage formatMessage;
	LecturersRepository repository;
	CoursesRepository coursesRepository;

	/**
	 * @param coursesRepository the coursesRepository to set
	 */
	public void setCoursesRepository(CoursesRepository coursesRepository) {
		this.coursesRepository = coursesRepository;
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
		String lang = "vi";
		BaseResponse res = BaseResponse.parse(Contants.ERROR_INTERNAL, formatMessage, lang);
		long start = System.currentTimeMillis();

		String TAG = "onSearchLecturers";
		try {

			ObjectLecturerRequest req = gson.fromJson(request, ObjectLecturerRequest.class);
			if (req == null) {
				throw new BusinessException(Contants.ERROR_INVALID_FORMAT);
			}
			CoursesLecturerResponse response = new CoursesLecturerResponse();
			Lecturers lecturers = repository.findLecturersById(req.getLecturerId());
			if (lecturers != null) {
				response.setLecturer(lecturers);
			}
			/**
			 * List khoa hoc cua giang vien
			 */
			List<Courses> listCourses = coursesRepository.findByLecturerId(req.getLecturerId());
			if (listCourses != null && !listCourses.isEmpty()) {
				response.setCoursers(listCourses);
			}

			res = BaseResponse.parse(Contants.SUCCESS, formatMessage, lang);
			res.setTotal(listCourses.size());
			res.setData(response);
			logger.debug("Response {}", gson.toJson(res));
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
