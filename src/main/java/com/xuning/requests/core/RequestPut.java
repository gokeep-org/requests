package com.xuning.requests.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.GsonBuilder;
import com.xuning.requests.exception.ErrorCode;
import com.xuning.requests.exception.RequestsException;
import com.xuning.requests.filter.RequestFilter;
import com.xuning.requests.util.RequestUtil;


public class RequestPut extends RequestClient<HttpPut> {
    private HttpPut httpPut = new HttpPut();
    private HttpResponse httpResponse = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestPut.class);

    @Override
    protected HttpPut setUpHttpMethod() {
        return httpPut;
    }

    @Override
    protected HttpResponse sendRequest(String url, Map<String, String> params, Map<String, String> postForms, Object body, Map<String, String> headers) {
        if (!RequestFilter.checkHeaderisNull(headers)) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                httpPut.setHeader(header.getKey(), header.getValue());
            }
        }
        this.httpPut.setURI(RequestUtil.getURI(url));
        if (!RequestFilter.checkParamsIsNull(params)) {
            this.httpPut.setURI(
                    RequestUtil.getURI(url + RequestUtil.buildParames(params))
            );
        }

        if (!RequestFilter.checkPostFormIsNull(postForms)) {
            try {
                httpPut.setEntity(RequestUtil.toHttpEntity(postForms));
            } catch (UnsupportedEncodingException e) {
                throw new RequestsException(ErrorCode.POST_FORM_TRANSLATION_ERROR);
            }
        }

        if (!RequestFilter.checkBodyIsNull(body)) {
            String bodyString = null;
            if (body instanceof String) {
                bodyString = body.toString();
            } else {
                bodyString = new GsonBuilder().create().toJson(body);
            }
            httpPut.setEntity(RequestUtil.toStringEntity(bodyString));
        }

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setSocketTimeout(DefaultConfig.SOCKET_TIMOUT)
                .setConnectTimeout(DefaultConfig.CONNECTION_TIMOUT)
                .build();
        this.httpPut.setConfig(requestConfig);
        try {
            httpResponse = httpClient.execute(this.httpPut);
        } catch (IOException e) {
            throw new RequestsException(ErrorCode.RESULT_ERROR);
        }
        return httpResponse;
    }
}
