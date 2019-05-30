/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.repository.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stfc.business.BusinessException;
import org.stfc.dto.Officers;
import org.stfc.dto.Positions;
import org.stfc.dto.Questions;
import org.stfc.dto.SurveyResults;
import org.stfc.dto.Surveys;
import org.stfc.entity.SurveyImportRequest;
import org.stfc.repository.OfficersRepository;
import org.stfc.repository.PositionsRepository;
import org.stfc.repository.QuestionsRepository;
import org.stfc.repository.SurveyResultsRepository;
import org.stfc.repository.SurveysRepository;
import org.stfc.utils.Comparator;
import org.stfc.utils.Constants;
import org.stfc.utils.DateTimeUtils;
import org.stfc.utils.StringUtils;

/**
 *
 * @author dongdv
 */
@Repository
public class SurveysRepositoryImpl {

    private static final Logger logger = LoggerFactory.getLogger(SurveysRepositoryImpl.class);
    @Autowired
    EntityManager em;
    @Autowired
    SurveysRepository surveysRepository;
    @Autowired
    SurveyResultsRepository surveyResultsRepository;
    @Autowired
    QuestionsRepository questionsRepository;
    @Autowired
    QuestionsRepositoryImpl questionsRepositoryImpl;
    @Autowired
    PositionsRepository positionsRepository;
    @Autowired
    PositionsRepositoryImpl positionsRepositoryImpl;
    @Autowired
    OfficersRepository officersRepository;
    @Autowired
    OfficersRepositoryImpl officersRepositoryImpl;

    public List<Surveys> onSearch(Surveys survey) {
        StringBuilder sql = new StringBuilder("SELECT * FROM surveys u WHERE 1 = 1");
        if (!Comparator.isEqualNull(survey)) {
            if (!Comparator.isEqualNull(survey.getSurveyId())) {
                sql.append(" AND u.survey_id = :surveyId");
            }
            if (!Comparator.isEqualNullOrEmpty(survey.getSurveyTitle())) {
                sql.append(" AND u.survey_title like :surveyTitle escape '/'");
            }
            if (!Comparator.isEqualNullOrEmpty(survey.getSurveyDescription())) {
                sql.append(" AND u.survey_description like :surveyDescription escape '/'");
            }
            if (!Comparator.isEqualNull(survey.getStartTime())) {
                sql.append(" AND u.start_time <= :startTime");
            }
            if (!Comparator.isEqualNull(survey.getEndTime())) {
                sql.append(" AND u.end_time <= :endTime");
            }
            if (!Comparator.isEqualNullOrEmpty(survey.getKeySearch())) {
                sql.append(" AND MATCH (u.survey_title, u.survey_description) AGAINST (:keySearch)");
            }
        }
        Query query = em.createNativeQuery(sql.toString(), Surveys.class);

        if (!Comparator.isEqualNull(survey)) {
            if (!Comparator.isEqualNull(survey.getSurveyId())) {
                query.setParameter("surveyId", survey.getSurveyId());
            }
            if (!Comparator.isEqualNullOrEmpty(survey.getSurveyTitle())) {
                query.setParameter("surveyTitle", "%" + StringUtils.escapeCharacter(survey.getSurveyTitle()) + "%");
            }
            if (!Comparator.isEqualNullOrEmpty(survey.getSurveyDescription())) {
                query.setParameter("surveyDescription", "%" + StringUtils.escapeCharacter(survey.getSurveyDescription()) + "%");
            }
            if (!Comparator.isEqualNull(survey.getStartTime())) {
                query.setParameter("startTime", survey.getStartTime());
            }
            if (!Comparator.isEqualNull(survey.getEndTime())) {
                query.setParameter("endTime", survey.getEndTime());
            }
            if (!Comparator.isEqualNullOrEmpty(survey.getKeySearch())) {
                query.setParameter("keySearch", survey.getKeySearch());
            }
        }
        return query.getResultList();
    }

    public void importSurveyResult(List<SurveyImportRequest> surveyImportRequests) throws Exception {
        if (Comparator.isEqualNullOrEmpty(surveyImportRequests)) {
            throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
        }
        for (SurveyImportRequest surveyImportRequest : surveyImportRequests) {
            //validate
            if (Comparator.isEqualNullOrEmpty(surveyImportRequest.getSurveyTitle())) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            if (Comparator.isEqualNullOrEmpty(surveyImportRequest.getFullName())) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            if (Comparator.isEqualNullOrEmpty(surveyImportRequest.getEmail())) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            if (Comparator.isEqualNullOrEmpty(surveyImportRequest.getPositionName())) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            if (Comparator.isEqualNullOrEmpty(surveyImportRequest.getQuestionContent())) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            if (Comparator.isEqualNullOrEmpty(surveyImportRequest.getAnswer())) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }

            //survey
            Surveys surveyInput = new Surveys();
            surveyInput.setSurveyTitle(surveyImportRequest.getSurveyTitle());
            List<Surveys> listSurveys = onSearch(surveyInput);
            Long surveyId = null;
            if (!Comparator.isEqualNullOrEmpty(listSurveys)) {
                surveyId = listSurveys.get(0).getSurveyId();
            } else {
                surveyInput.setSurveyDescription(surveyImportRequest.getSurveyTitle());
                surveyInput.setStartTime(DateTimeUtils.truncYear(new Date()));
                surveyInput.setEndTime(DateTimeUtils.truncYear(DateTimeUtils.addYear(new Date(), 1)));
                surveyInput.setCreatedDate(new Date());
                surveyInput.setUpdatedDate(new Date());
                surveyInput = surveysRepository.save(surveyInput);
                surveyId = surveyInput.getSurveyId();
            }
            logger.info("surveyId: " + surveyId);
            //position
            Positions positionInput = new Positions();
            positionInput.setPositionName(surveyImportRequest.getPositionName());
            List<Positions> listPositions = positionsRepositoryImpl.onSearch(positionInput);
            Long positionId = null;
            if (!Comparator.isEqualNullOrEmpty(listPositions)) {
                positionId = listPositions.get(0).getPositionId();
            } else {
                positionInput.setPositionName(surveyImportRequest.getPositionName());
                positionInput.setStatus(Constants.STATUS.ACTIVE);
                positionInput.setCreatedDate(new Date());
                positionInput.setUpdatedDate(new Date());
                positionInput = positionsRepository.save(positionInput);
                positionId = positionInput.getPositionId();
            }
            logger.info("positionId: " + positionId);
            //officer
            Officers officerInput = new Officers();
            officerInput.setEmail(surveyImportRequest.getEmail());
            List<Officers> listOfficers = officersRepositoryImpl.onSearch(officerInput);
            Long officerId = null;
            if (!Comparator.isEqualNullOrEmpty(listOfficers)) {
                officerId = listOfficers.get(0).getOfficerId();
            } else {
                officerInput.setFirstName(surveyImportRequest.getFirstName());
                officerInput.setLastName(surveyImportRequest.getLastName());
                officerInput.setFullName(surveyImportRequest.getFullName());
                officerInput.setMobile(surveyImportRequest.getMobile());
                officerInput.setPositionId(positionId);
                officerInput.setStatus(Constants.STATUS.ACTIVE);
                officerInput.setCreatedDate(new Date());
                officerInput.setUpdatedDate(new Date());
                officerInput = officersRepository.save(officerInput);
                officerId = officerInput.getOfficerId();
            }
            logger.info("officerId: " + officerId);
            //question
            Questions questionInput = new Questions();
            questionInput.setQuestionContent(surveyImportRequest.getQuestionContent());
            List<Questions> listQuestions = questionsRepositoryImpl.onSearch(questionInput);
            Long questionId = null;
            if (!Comparator.isEqualNullOrEmpty(listQuestions)) {
                questionId = listQuestions.get(0).getQuestionId();
            } else {
                questionInput.setQuestionType(Constants.QUESTION_TYPE.SINGE);
                questionInput.setCreatedDate(new Date());
                questionInput.setUpdatedDate(new Date());
                questionInput = questionsRepository.save(questionInput);
                questionId = questionInput.getQuestionId();
            }
            logger.info("questionId: " + questionId);

            //survey result
            SurveyResults surveyResults = new SurveyResults();
            surveyResults.setOfficerId(officerId);
            surveyResults.setQuestionId(questionId);
            surveyResults.setSurveyId(surveyId);
            surveyResults.setAnswer(surveyImportRequest.getAnswer());
            surveyResults.setCreatedDate(new Date());
            surveyResults = surveyResultsRepository.save(surveyResults);

            logger.info("surveyResultId: " + surveyResults.getSurveyResultId());
        }
    }
}
