package com.stfc.utils;

public interface Constants {

    public interface CONFIG {

        String SESSION_FACTORY = "sessionFactory";
        String TRANSACTION = "transaction";
    }

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
        String SERVER_ERROR = "500";
        String NOT_FOUND = "404";
    }
    public interface MESSAGE {

        String NOT_FOUND = "Not found!";
    }

    public interface QUESTION_TYPE {

        Integer SINGE = 1;
    }

    public interface DATE_FORMAT {

        String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
        String TIMEZONE_HCM = "Asia/Ho_Chi_Minh";
        String YYYY_MM_DD = "yyyy-MM-dd";
    }

    public interface EXPROT {

        String PATH_TEMP = "temp";
        String PATH_REPORT = "report";
        String PATH_FILE_SURVEY_TEMPLATE = "Survey.xlsx";
    }
}
