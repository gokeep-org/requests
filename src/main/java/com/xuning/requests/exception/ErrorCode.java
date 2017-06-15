package com.xuning.requests.exception;

public class ErrorCode {
    private ErrorCode() {
    }

    public static final String RESULT_ERROR = "request获取响应失败";
    public static final String RESULT_TO_STR_IS_ERROR = "request 转化字符失败";
    public static final String CLOSE_CLIENT_ERROR = "request获取响应失败";
    public static final String NOT_FOUND_URL = "Error: 请求没有发现url";
    public static final String POST_FORM_TRANSLATION_ERROR = "Error: \"postform装换失败\"";

}
