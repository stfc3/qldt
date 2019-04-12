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
    String ERROR_LOGIN = "SVC-LOGIN-01";

    String FORMAT_DATE = "dd/MM/yyyy HH:mm:ss";

    public interface PATH {
        String API_LOGIN = "/api/v1/auth/login";
        String API_CHANGE_PASS = "/api/v1/auth/changepass";
        String API_USERS = "/api/v1/users";
        String API_USERROLES = "/api/v1/userroles";
        String API_USERS_SEARCH = "/api/v1/users/search";
        String API_ROLES = "/api/v1/roles";
        String API_ROLEOBJECTS = "/api/v1/roleobjects";
        String API_ROLES_SEARCH = "/api/v1/roles/search";
        String API_OBJECTS = "/api/v1/objects";
        String API_OBJECTS_SEARCH = "/api/v1/objects/search";
    }
}
