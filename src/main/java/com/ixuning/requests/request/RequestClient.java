package com.ixuning.requests.request;

import com.ixuning.requests.bean.exception.RequestsForXNException;
import com.ixuning.requests.core.Config;
import com.ixuning.requests.request.factory.RequestFactory;
import com.ixuning.requests.request.intercept.RequestIntercept;
import com.ixuning.requests.util.log.LogUtil;
import com.ixuning.requests.util.log.Logger;
import com.ixuning.requests.util.log.LoggerFactory;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RequestClient {
    private String url;
    private int sendRes;
    private String method;
    private String postBody;
    private StringEntity stringEntity;
    private static List<Header> headers;
    private List<NameValuePair> nameValuePairs;
    private static final Lock lock = new ReentrantLock();
    private static Logger logger = LoggerFactory.getLogger(RequestClient.class);

    public RequestClient openRequest(String url, String method, List<Header> headers, List<NameValuePair> nameValuePairs, String postBody) {
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.nameValuePairs = nameValuePairs;
        this.postBody = postBody;
        return this;
    }

    public HttpResponse sendRequest() {
        HttpResponse httpResponse = null;
            RequestOperation requestOperation = RequestFactory.getRequestMethod(this);
            httpResponse = requestOperation.execute();
            sendRes = httpResponse.getStatusLine().getStatusCode();
            logger.info(this.toString());
            if (sendRes == 200) {
                return httpResponse;
            }
            else {
                RequestIntercept.ErrorInfoIntercept(httpResponse);
            }
        return httpResponse;
    }
    public String getStringResponse() {
        String errorFalg=UUID.randomUUID().toString();
        System.out.println(errorFalg);
        String strRes= errorFalg;
        RequestOperation requestOperation = RequestFactory.getRequestMethod(this);
        HttpResponse httpResponse = requestOperation.execute();
        sendRes = httpResponse.getStatusLine().getStatusCode();
        logger.info(this.toString());
        if (sendRes == 200) {
            try {
                strRes= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                return strRes;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return strRes;

        }
        else {
            RequestIntercept.ErrorInfoIntercept(httpResponse);
        }
        if (errorFalg == strRes){
            new RequestsForXNException("string httpresponse is null");
        }
        return strRes;
    }
    public String getJsonResponse() {
        String errorFalg=UUID.randomUUID().toString();
        System.out.println(errorFalg);
        String strRes= errorFalg;
        RequestOperation requestOperation = RequestFactory.getRequestMethod(this);
        HttpResponse httpResponse = requestOperation.execute();
        sendRes = httpResponse.getStatusLine().getStatusCode();
        logger.info(this.toString());
        if (sendRes == 200) {
            try {
                strRes= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                return new Gson().toJson(strRes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            RequestIntercept.ErrorInfoIntercept(httpResponse);
        }
        if (errorFalg == strRes){
            new RequestsForXNException("string httpresponse is null");
        }
        return strRes;
    }

    public static void logRequest(String str) {
        LogUtil.getLogUtil().printLog(str);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public List<NameValuePair> getNameValuePairs() {
        return nameValuePairs;
    }

    public void setNameValuePairs(List<NameValuePair> nameValuePairs) {
        this.nameValuePairs = nameValuePairs;
    }

    public StringEntity getStringEntity() {
        return stringEntity;
    }

    public void setStringEntity(StringEntity stringEntity) {
        this.stringEntity = stringEntity;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public RequestClient openRequest(String url, String method) {
        return openRequest(url, method, null, null, null);
    }

    public RequestClient openRequest(String url, String method, List<Header> headers) {
        return openRequest(url, method, headers, null, null);
    }

    public RequestClient openRequest(String url, String method, List<Header> headers, List<NameValuePair> nameValuePairs) {
        return openRequest(url, method, headers, nameValuePairs, null);
    }

    @Override
    public String toString() {
        if (Config.OPEN_LOG_OUTPUT || Config.OPEN_LOG_PRINT) {
            String hRes = "";
            if(null!=headers){
                for (Header h : headers) {
                    hRes += (h.getKey() + ":" + h.getValue() + " ");
                }
            }
            String nRes = "";
            if (null != nameValuePairs) {
                for (NameValuePair nameValuePair : nameValuePairs) {
                    nRes += (nameValuePair.getName() + ":" + " ");
                }
            }
            return ("[" + "response code：" + sendRes + "] request info: [url: " + this.getUrl() +
                    "] [method:" + this.getMethod() + "] [header：" + hRes + "] [request option:" +
                    nRes + "] [postbody :" + postBody + "]" + "---");
        }
        return null;
    }

}
