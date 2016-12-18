package com.ixuning.requests.util;

import com.ixuning.requests.bean.exception.ExternalErrorCode;
import com.ixuning.requests.bean.exception.RequestsForXNException;
import com.ixuning.requests.core.Config;
import com.ixuning.requests.request.RequestClient;
import com.ixuning.requests.util.log.Logger;
import com.ixuning.requests.util.log.LoggerFactory;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuning on 2016/8/9.
 * 数据转换
 */
public final class TransformationUtil {
    private static Logger logger = LoggerFactory.getLogger(TransformationUtil.class);

    private TransformationUtil() {
    }

    /**
     * 转换为HttpEntity
     *
     * @param valuePairs
     * @return
     */
    public static final HttpEntity toHttpEntity(List<NameValuePair> valuePairs) {
        HttpEntity httpEntity;
        try {
            httpEntity = new UrlEncodedFormEntity(valuePairs, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            throw new RequestsForXNException(ExternalErrorCode.UN_SUPPORT_ENCODING);
        }
        return httpEntity;
    }

    /**
     * 转换为StringEntity
     *
     * @param jsonStrintg
     * @return
     */
    public static final StringEntity toStringEntity(String jsonStrintg) {
        StringEntity stringEntity = new StringEntity(jsonStrintg, Config.DEFAULT_CHARSET_TYPE);
        return stringEntity;
    }

    /**
     * 转换为String
     *
     * @param httpResponse
     * @return
     */
    public static final String httpResponseToString(HttpResponse httpResponse) {
        String res = null;
        try {
            res = EntityUtils.toString(httpResponse.getEntity());
        }
        catch (IOException e) {
            throw new RequestsForXNException(e.getMessage());
        }
        return res;
    }

    /**
     * httpresponse转换为Object
     *
     * @param httpResponse
     * @param classes
     * @return
     */
    public static final Object httpResponseToObject(HttpResponse httpResponse, Class classes) {
        String res = httpResponseToString(httpResponse);
        if (null == res) {
            throw new RequestsForXNException(ExternalErrorCode.HTTP_RESPONSE_IS_NULL);
        }
        return new Gson().fromJson(res, classes);
    }

    /**
     * 直接取出Client转换为Object
     *
     * @param requestClient
     * @param classes
     * @return
     */
    public static final Object requestClientToOutputObject(RequestClient requestClient, Class classes) {
        HttpResponse httpResponse = requestClient.sendRequest();
        String res = httpResponseToString(httpResponse);
        logger.debug(res);
        Object o = null;
        try {
            o = new Gson().fromJson(res, classes);
        }
        catch (Exception e) {
            throw new RequestsForXNException(ExternalErrorCode.HTTP_RESPONSE_IS_NULL);
        }
        return o;
    }

    /**
     * postBody转换为JsonString
     *
     * @param o
     * @return
     */
    public static final String postBodyObjToJsonString(Object o) {
        return new Gson().toJson(o);
    }

    /**
     * 数组转化为List
     *
     * @param ids
     * @return
     */
    public static List<Long> ArrToArrayListpostBody(long... ids) {
        List<Long> idsList = new ArrayList<Long>();
        if (null == ids) {
            return null;
        }
        for (long id : ids) {
            idsList.add(id);
        }
        return idsList;
    }
}
