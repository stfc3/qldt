/**
 *
 */
package org.stfc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.stfc.message.BaseResponse;
import org.stfc.utils.Constants;
import org.stfc.utils.FormatMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.stfc.business.BusinessException;
import org.stfc.dto.Answers;
import org.stfc.dto.Officers;
import org.stfc.dto.Positions;
import org.stfc.dto.Questions;
import org.stfc.dto.SurveyResults;
import org.stfc.dto.Surveys;
import org.stfc.entity.ExportSurvey;
import org.stfc.entity.SurveyImportRequest;
import org.stfc.excel.ExcelUtils;
import org.stfc.repository.AnswersRepository;
import org.stfc.repository.OfficersRepository;
import org.stfc.repository.PositionsRepository;
import org.stfc.repository.QuestionsRepository;
import org.stfc.repository.SurveyResultsRepository;
import org.stfc.repository.SurveysRepository;
import org.stfc.repository.impl.AnswersRepositoryImpl;
import org.stfc.repository.impl.OfficersRepositoryImpl;
import org.stfc.repository.impl.PositionsRepositoryImpl;
import org.stfc.repository.impl.QuestionsRepositoryImpl;
import org.stfc.repository.impl.SurveysRepositoryImpl;
import org.stfc.utils.Comparator;
import org.stfc.utils.DateTimeUtils;
import org.stfc.utils.IntrospectorUtils;

/**
 * @author dongdv
 *
 */
@RestController
public class SurveyController {

    private static final Logger logger = LoggerFactory.getLogger(SurveyController.class);
    @Autowired
    SurveyResultsRepository surveyResultsRepository;
    @Autowired
    SurveysRepository surveysRepository;
    @Autowired
    SurveysRepositoryImpl surveysRepositoryImpl;
    @Autowired
    QuestionsRepository questionsRepository;
    @Autowired
    QuestionsRepositoryImpl questionsRepositoryImpl;
    @Autowired
    AnswersRepository answersRepository;
    @Autowired
    AnswersRepositoryImpl answersRepositoryImpl;
    @Autowired
    PositionsRepository positionsRepository;
    @Autowired
    PositionsRepositoryImpl positionsRepositoryImpl;
    @Autowired
    OfficersRepository officersRepository;
    @Autowired
    OfficersRepositoryImpl officersRepositoryImpl;
    @Autowired
    FormatMessage formatMessage;
    @Autowired
    ServletContext context;

    @RequestMapping(value = Constants.PATH.API_SURVEY_IMPORT, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String importSurveyResult(@RequestBody List<SurveyImportRequest> surveyImportRequests) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(surveyImportRequests));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNull(surveyImportRequests)) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            importSurveyData(surveyImportRequests);
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_SURVEYS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String insertSurvey(@RequestBody List<Surveys> surveys) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(surveys));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNull(surveys)) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            for (Surveys survey : surveys) {
                survey.setCreatedDate(new Date());
                surveysRepository.save(survey);
            }
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_SURVEYS, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateSurvey(@RequestBody Surveys survey) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(survey));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNull(survey) || Comparator.isEqualNull(survey.getSurveyId())) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            Surveys surveyCurrent = surveysRepository.getOne(survey.getSurveyId());
            if (Comparator.isEqualNull(surveyCurrent)) {
                throw new BusinessException(Constants.ERROR_INTERNAL);
            } else {
                IntrospectorUtils.cloneEntity(surveyCurrent, survey);
                surveyCurrent.setUpdatedDate(new Date());
                surveysRepository.save(surveyCurrent);
                response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            }
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_SURVEYS_SEARCH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchSurvey(@RequestBody(required = false) Surveys survey) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(survey));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            List<Surveys> listSurveys = surveysRepositoryImpl.onSearch(survey);
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            if (!Comparator.isEqualNull(listSurveys)) {
                response.setTotal(listSurveys.size());
                response.setData(listSurveys);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_SURVEY_QUESTIONS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String insertQuestion(@RequestBody List<Questions> questions) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(questions));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNull(questions)) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            for (Questions question : questions) {
                question.setCreatedDate(new Date());
                questionsRepository.save(question);
            }
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_SURVEY_QUESTIONS_SEARCH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchQuestion(@RequestBody(required = false) Questions question) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(question));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            List<Questions> listQuestion = questionsRepositoryImpl.onSearch(question);
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            if (!Comparator.isEqualNull(listQuestion)) {
                response.setTotal(listQuestion.size());
                response.setData(listQuestion);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_SURVEY_QUESTIONS, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateQuestion(@RequestBody Questions question) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(question));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNull(question) || Comparator.isEqualNull(question.getQuestionId())) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            Questions questionCurrent = questionsRepository.getOne(question.getQuestionId());
            if (Comparator.isEqualNull(questionCurrent)) {
                throw new BusinessException(Constants.ERROR_INTERNAL);
            } else {
                IntrospectorUtils.cloneEntity(questionCurrent, question);
                questionCurrent.setUpdatedDate(new Date());
                questionsRepository.save(questionCurrent);
                response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            }
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_SURVEY_ANSWERS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String insertAnswer(@RequestBody List<Answers> answers) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(answers));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNull(answers)) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            for (Answers Answer : answers) {
                Answer.setCreatedDate(new Date());
                answersRepository.save(Answer);
            }
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_SURVEY_ANSWERS_SEARCH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchAnswer(@RequestBody(required = false) Answers answer) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(answer));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            List<Answers> listaAnswers = answersRepositoryImpl.onSearch(answer);
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            if (!Comparator.isEqualNull(listaAnswers)) {
                response.setTotal(listaAnswers.size());
                response.setData(listaAnswers);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_SURVEY_ANSWERS, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateAnswer(@RequestBody Answers answer) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        logger.debug("Body request: {}", gson.toJson(answer));
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            if (Comparator.isEqualNull(answer) || Comparator.isEqualNull(answer.getAnswerId())) {
                throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
            }
            Answers answerCurrent = answersRepository.getOne(answer.getAnswerId());
            if (Comparator.isEqualNull(answerCurrent)) {
                throw new BusinessException(Constants.ERROR_INTERNAL);
            } else {
                IntrospectorUtils.cloneEntity(answerCurrent, answer);
                answerCurrent.setUpdatedDate(new Date());
                answersRepository.save(answerCurrent);
                response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            }
        } catch (BusinessException be) {
            response = BaseResponse.parse(be.getMessage(), formatMessage, lang);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    private void importSurveyData(List<SurveyImportRequest> surveyImportRequests) throws Exception {
        if (Comparator.isEqualNullOrEmpty(surveyImportRequests)) {
            throw new BusinessException(Constants.ERROR_INVALID_FORMAT);
        }
        for (SurveyImportRequest surveyImportRequest : surveyImportRequests) {
            // validate
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

            // survey
            Surveys surveyInput = new Surveys();
            surveyInput.setSurveyTitle(surveyImportRequest.getSurveyTitle());
            List<Surveys> listSurveys = surveysRepositoryImpl.onSearch(surveyInput);
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
            // position
            Positions positionInput = new Positions();
            positionInput.setPositionType(surveyImportRequest.getPositionType());
            positionInput.setPositionName(surveyImportRequest.getPositionName());
            List<Positions> listPositions = positionsRepositoryImpl.onSearch(positionInput);
            Long positionId = null;
            if (!Comparator.isEqualNullOrEmpty(listPositions)) {
                positionId = listPositions.get(0).getPositionId();
            } else {
                positionInput.setStatus(Constants.STATUS.ACTIVE);
                positionInput.setCreatedDate(new Date());
                positionInput.setUpdatedDate(new Date());
                positionInput = positionsRepository.save(positionInput);
                positionId = positionInput.getPositionId();
            }
            logger.info("positionId: " + positionId);
            // officer
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
                officerInput.setGender(surveyImportRequest.getGender());
                officerInput.setMobile(surveyImportRequest.getMobile());
                officerInput.setPositionId(positionId);
                officerInput.setStatus(Constants.STATUS.ACTIVE);
                officerInput.setCreatedDate(new Date());
                officerInput.setUpdatedDate(new Date());
                officerInput = officersRepository.save(officerInput);
                officerId = officerInput.getOfficerId();
            }
            logger.info("officerId: " + officerId);
            // question
            Questions questionInput = new Questions();
            questionInput.setQuestionCode(surveyImportRequest.getQuestionCode());
            List<Questions> listQuestions = questionsRepositoryImpl.onSearch(questionInput);
            Long questionId = null;
            if (!Comparator.isEqualNullOrEmpty(listQuestions)) {
                questionId = listQuestions.get(0).getQuestionId();
            } else {
                questionInput.setQuestionContent(surveyImportRequest.getQuestionContent());
                questionInput.setQuestionType(Constants.QUESTION_TYPE.SINGE);
                questionInput.setSurveyId(surveyId);
                questionInput.setCreatedDate(new Date());
                questionInput.setUpdatedDate(new Date());
                questionInput = questionsRepository.save(questionInput);
                questionId = questionInput.getQuestionId();
            }
            logger.info("questionId: " + questionId);

            // survey result
            SurveyResults surveyResults = new SurveyResults();
            surveyResults.setOfficerId(officerId);
            surveyResults.setQuestionId(questionId);
            surveyResults.setSurveyId(surveyId);
            surveyResults.setAnswer(surveyImportRequest.getAnswer());
            surveyResults.setCreatedDate(new Date());
            surveyResults.setLearnFromDate(surveyImportRequest.getLearnFromDate());
            surveyResults.setLearnToDate(surveyImportRequest.getLearnToDate());
            surveyResults = surveyResultsRepository.save(surveyResults);

            logger.info("surveyResultId: " + surveyResults.getSurveyResultId());
        }
    }

    @RequestMapping(value = Constants.PATH.API_SURVEY_EXPORT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String exportSurvey(@RequestParam @DateTimeFormat(pattern = Constants.DATE_FORMAT.YYYY_MM_DD) Date fromDate,
            @RequestParam @DateTimeFormat(pattern = Constants.DATE_FORMAT.YYYY_MM_DD) Date toDate,
            @RequestParam(required = false) String positionType) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String lang = "vi";
        BaseResponse response = BaseResponse.parse(Constants.ERROR_INTERNAL, formatMessage, lang);
        try {
            List<ExportSurvey> listExportSurveys = surveysRepositoryImpl.exportSurvey(fromDate, toDate, positionType);
            response = BaseResponse.parse(Constants.SUCCESS, formatMessage, lang);
            if (!Comparator.isEqualNull(listExportSurveys)) {
                response.setTotal(listExportSurveys.size());
                response.setData(listExportSurveys);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return gson.toJson(response);
    }

    @RequestMapping(value = Constants.PATH.API_SURVEY_EXPORT_DOWNLOAD, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> download(@RequestParam @DateTimeFormat(pattern = Constants.DATE_FORMAT.YYYY_MM_DD) Date fromDate,
            @RequestParam @DateTimeFormat(pattern = Constants.DATE_FORMAT.YYYY_MM_DD) Date toDate,
            @RequestParam(required = false) String positionType) {

        long startTime = System.currentTimeMillis();
        long processDuration = 0;
        String contentType = null;
        InputStreamResource resource = null;
        try {
            logger.info("START download");

            ExcelUtils excelUtils = new ExcelUtils();
            StringBuilder pathFileInput = new StringBuilder("/config/temp/");
            pathFileInput.append(Constants.EXPROT.PATH_FILE_SURVEY_TEMPLATE);
            pathFileInput.append(Constants.EXPROT.DOT);
            pathFileInput.append(Constants.EXPROT.EXCEL_EXTENSION);
            ClassPathResource resourceTemplate = new ClassPathResource(pathFileInput.toString());
            String filePathTemp = resourceTemplate.getPath();
            String pathFolderOutput = context.getRealPath(Constants.EXPROT.PATH_REPORT);
            File folderOutput = new File(pathFolderOutput);
            if (!folderOutput.exists()) {
                folderOutput.mkdir();
            }
            StringBuilder filePathOutput = new StringBuilder(pathFolderOutput);
            filePathOutput.append(File.separator);
            filePathOutput.append(Constants.EXPROT.PATH_FILE_SURVEY_TEMPLATE);
            filePathOutput.append(Constants.EXPROT.UNDERLINE);
            if (!Comparator.isEqualNullOrEmpty(positionType)) {
                filePathOutput.append(positionType);
                filePathOutput.append(Constants.EXPROT.UNDERLINE);
            }
            filePathOutput.append(DateTimeUtils.convestDateToString(fromDate, Constants.DATE_FORMAT.YYYYMMDD));
            filePathOutput.append(Constants.EXPROT.UNDERLINE);
            filePathOutput.append(DateTimeUtils.convestDateToString(toDate, Constants.DATE_FORMAT.YYYYMMDD));
            filePathOutput.append(Constants.EXPROT.DOT);
            filePathOutput.append(Constants.EXPROT.EXCEL_EXTENSION);
            
            List<ExportSurvey> listExportSurveys = surveysRepositoryImpl.exportSurvey(fromDate, toDate, positionType);
            excelUtils.write(listExportSurveys, filePathTemp, filePathOutput.toString());
            File fileExport = new File(filePathOutput.toString());

            contentType = context.getMimeType(fileExport.getAbsolutePath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            logger.info("contentType: " + contentType);

            resource = new InputStreamResource(new FileInputStream(fileExport));
            processDuration = System.currentTimeMillis() - startTime;
            logger.info("END download in " + processDuration + "ms");
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileExport.getName())
                    .contentType(MediaType.parseMediaType(contentType))
                    .contentLength(fileExport.length())
                    .body(resource);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            processDuration = System.currentTimeMillis() - startTime;
            logger.info("END download in " + processDuration + "ms");
        }

        return null;
    }
}
