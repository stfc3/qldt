package org.stfc.utils;

public interface Constants {

    String LOG_FORMAT_TIME = "[{}]-{}:{} ms";
    String SUCCESS = "STFC-SUCCESS-00";
    String ERROR_INVALID_FORMAT = "STFC-INVALID-04";
    String ERROR_INTERNAL = "STFC-INTERNAL-07";
    String ERROR_DATA_EMPTY = "STFC-DATA-01";
    String ERROR_ID_EMPTY = "STFC-ID-02";
    String ERROR_POSITION_EMPTY = "STFC-INVALID-POSITION-03";
    String ERROR_CERTIFICATE_EMPTY = "STFC-INVALID-CERTIFICATE-04";
    String STATUS_00 = "STATUS-00";
    String STATUS_01 = "STATUS-01";
    String FORMAT_DATE = "dd/MM/yyyy HH:mm:ss";

    public interface PATH {
        String API_SURVEY_IMPORT = "/api/v1/survey/import";
        String API_SURVEY_EXPORT = "/api/v1/survey/export";
        String API_SURVEY_EXPORT_DOWNLOAD = "/api/v1/survey/export/download";
        String API_SURVEYS = "/api/v1/surveys";
        String API_SURVEYS_SEARCH = "/api/v1/surveys/search";
        String API_SURVEY_QUESTIONS = "/api/v1/survey/questions";
        String API_SURVEY_QUESTIONS_SEARCH = "/api/v1/survey/questions/search";
        String API_SURVEY_ANSWERS = "/api/v1/survey/answers";
        String API_SURVEY_ANSWERS_SEARCH = "/api/v1/survey/answers/search";
        
        String API_OFFICER = "/api/v1/officer";
    }

    public interface STATUS {
        Integer ACTIVE = 1;
    }
    public interface QUESTION_TYPE {
        Integer SINGE = 1;
    }
    public interface DATE_FORMAT{
        String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
        String TIMEZONE_HCM = "Asia/Ho_Chi_Minh";
        String YYYY_MM_DD = "yyyy-MM-dd";
        String YYYYMMDD = "yyyyMMdd";
    }
     public interface EXPROT {
        String PATH_TEMP="temp";
        String PATH_REPORT="report";
        String PATH_FILE_SURVEY_TEMPLATE="Survey";
        String EXCEL_EXTENSION="xlsx";
        String DOT=".";
        String UNDERLINE="_";
    }
}
