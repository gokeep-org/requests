package com.xuning.requests.core;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.GsonBuilder;
import com.xuning.requests.bean.HttpDelete;
import com.xuning.requests.exception.ErrorCode;
import com.xuning.requests.exception.RequestsException;
import com.xuning.requests.filter.RequestFilter;
import com.xuning.requests.util.RequestUtil;


public class RequestDelete extends com.xuning.requests.core.RequestClient<HttpDelete> {
    private HttpResponse httpResponse;
    private HttpDelete httpDelete = new HttpDelete();
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestDelete.class);

    @Override
    protected HttpDelete setUpHttpMethod() {
        return httpDelete;
    }

    @Override
    protected HttpResponse sendRequest(String url, Map<String, String> params, Map<String, String> postForms, Object body, Map<String, String> headers) {
        if (!RequestFilter.checkHeaderisNull(headers)) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                httpDelete.setHeader(header.getKey(), header.getValue());
            }
        }
        this.httpDelete.setURI(RequestUtil.getURI(url));
        if (!RequestFilter.checkParamsIsNull(params)) {
            this.httpDelete.setURI(
                    RequestUtil.getURI(url + RequestUtil.buildParames(params))
            );
        }

        if (!RequestFilter.checkBodyIsNull(body)) {
            String bodyString = null;
            if (body instanceof String) {
                bodyString = body.toString();
            } else {
                bodyString = new GsonBuilder().create().toJson(body);
            }
            httpDelete.setEntity(RequestUtil.toStringEntity(bodyString));
        }

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setSocketTimeout(DefaultConfig.SOCKET_TIMOUT)
                .setConnectTimeout(DefaultConfig.CONNECTION_TIMOUT)
                .build();
        this.httpDelete.setConfig(requestConfig);
        try {
            httpResponse = httpClient.execute(this.httpDelete);
        } catch (IOException e) {
            throw new RequestsException(ErrorCode.RESULT_ERROR);
        }
        return httpResponse;
    }
}
