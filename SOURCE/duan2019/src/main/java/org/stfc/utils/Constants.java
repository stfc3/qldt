package org.stfc.utils;

public interface Constants {

    String LOG_FORMAT_TIME = "[{}]-{}:{} ms";
    String SUCCESS = "SVC-SUCCESS-00";
    String ERROR_COMMON = "SVC-Common-08";
    String ERROR_INVALID_FORMAT = "SVC-INVALID-04";
    String ERROR_INVALID_TOKEN = "SVC-INVALIDTOKEN-19";
    String ERROR_DUPLICATE_NONE = "SVC-NONCE-02";
    String ERROR_INTERNAL = "SVC-INTERNAL-07";
    String ERROR_CAMPAIN = "SVC-CAMPAIN-10";

    String FORMAT_DATE = "dd/MM/yyyy HH:mm:ss";

    public interface PATH {
        String API_SURVEY_IMPORT = "/api/v1/survey/import";
    }
}
