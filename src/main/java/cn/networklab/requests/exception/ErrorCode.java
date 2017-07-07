package cn.networklab.requests.exception;

public class ErrorCode {
    private ErrorCode() {
    }
    public static final String RESULT_ERROR = "Requests client request get response is fail";
    public static final String RESULT_TO_STR_IS_ERROR = "Requests client response transform string is fail";
    public static final String CLOSE_CLIENT_ERROR = "Requests client close is fail";
    public static final String NOT_FOUND_URL = "Requests client not find request url";
    public static final String POST_FORM_TRANSLATION_ERROR = "Requests client post form transform http entity is fail";

}
