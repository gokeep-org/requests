package cn.networklab.requests.core;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.networklab.requests.exception.ErrorCode;
import cn.networklab.requests.exception.RequestsException;


public abstract class RequestClient<T> {
    public static CloseableHttpClient httpClient;
    public static final Logger LOGGER = LoggerFactory.getLogger(RequestClient.class);
    private String loggerString = "";

    protected CloseableHttpClient openHttpClient() {
        //httpClient = HttpClients.createDefault();
        httpClient = cn.networklab.requests.core.HttpConnectionManager.getHttpClient();
        return httpClient;
    }

    protected abstract T setUpHttpMethod();

    private void requestParamterFilter(String url) {
        if (null == url) {
            try {
                throw new RequestsException("request is bad, because url is null");
            } catch (RequestsException e) {
                throw new RequestsException("request is bad, because url is null", e);
            }
        }
    }

    protected abstract HttpResponse sendRequest(
            String url, Map<String, String> params, Map<String, String> postForms, Object body, Map<String, String> headers
    );

    protected cn.networklab.requests.bean.HttpResponse execute(String url) {
        return execute(url, null, null, null, null);
    }

    protected cn.networklab.requests.bean.HttpResponse execute(String url, Map<String, String> headers) {
        return execute(url, null, null, null, headers);
    }

    protected cn.networklab.requests.bean.HttpResponse execute(String url, Map<String, String> params, Map<String, String> headers) {
        return execute(url, params, null, null, headers);
    }

    protected cn.networklab.requests.bean.HttpResponse execute(String url, Map<String, String> params, Object body, Map<String, String> headers) {
        return execute(url, params, null, body, headers);
    }

    protected cn.networklab.requests.bean.HttpResponse execute(String url, Map<String, String> params, Map<String, String> postForms, Object body, Map<String, String> headers) {
        LOGGER.info("request: " + toLoggerMessage(url, params, headers, postForms, body));
        openHttpClient();
        setUpHttpMethod();
        requestParamterFilter(url);
        HttpResponse httpResponse = sendRequest(url, params, postForms, body, headers);
        closeHttpClient();
        return new cn.networklab.requests.bean.HttpResponse().setHttpResponse(httpResponse);
    }

    protected void closeHttpClient() {
        if (!Objects.equals(null, httpClient)) {
            try {
                if (Objects.equals(null, httpClient)) {
                    httpClient.close();
                }
            } catch (IOException e) {
                throw new RequestsException(ErrorCode.CLOSE_CLIENT_ERROR);
            }
        }
    }

    private String toLoggerMessage(String url, Map params, Map headers, Map postForm, Object body) {
        String res = String.format(cn.networklab.requests.core.DefaultConfig.LOGGER_FORMATE,
                url, headers, params, postForm, body);
        return res;
    }
}