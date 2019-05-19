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
    }
}
