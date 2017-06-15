package com.xuning.requests.filter;

import java.util.Map;
import java.util.Objects;


public class RequestFilter {
    private void RequestClient() {
    }

    public static Boolean checkHeaderisNull(Map<String, String> headers) {
        return checkMapObjectIsNull(headers);
    }

    public static Boolean checkParamsIsNull(Map<String, String> params) {
        return checkMapObjectIsNull(params);
    }

    public static Boolean checkPostFormIsNull(Map<String, String> postForms) {
        return checkMapObjectIsNull(postForms);
    }

    public static Boolean checkMapObjectIsNull(Map<String, String> map) {
        if (Objects.equals(null, map)) {
            return true;
        }
        if (map.size() == 0) {
            return true;
        }
        return false;
    }

    public static Boolean checkBodyIsNull(Object body) {
        if (Objects.equals(null, body)) {
            return true;
        }
        return false;
    }
}
