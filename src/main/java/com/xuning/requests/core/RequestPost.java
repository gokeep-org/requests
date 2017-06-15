package com.xuning.requests.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.GsonBuilder;
import com.xuning.requests.exception.ErrorCode;
import com.xuning.requests.exception.RequestsException;
import com.xuning.requests.filter.RequestFilter;
import com.xuning.requests.util.RequestUtil;


public class RequestPost extends RequestClient<HttpPost> {
    private HttpResponse httpResponse = null;
    private HttpPost httpPost = new HttpPost();
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestPost.class);

    @Override
    protected HttpPost setUpHttpMethod() {
        return httpPost;
    }

    @Override
    protected HttpResponse sendRequest(String url, Map<String, String> params, Map<String, String> postForms, Object body, Map<String, String> headers) {
        if (!RequestFilter.checkHeaderisNull(headers)) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                httpPost.setHeader(header.getKey(), header.getValue());
            }
        }
        this.httpPost.setURI(RequestUtil.getURI(url));
        if (!RequestFilter.checkParamsIsNull(params)) {
            this.httpPost.setURI(
                    RequestUtil.getURI(url + RequestUtil.buildParames(params))
            );
        }

        if (!RequestFilter.checkPostFormIsNull(postForms)) {
            try {
                httpPost.setEntity(RequestUtil.toHttpEntity(postForms));
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
            httpPost.setEntity(RequestUtil.toStringEntity(bodyString));
        }

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setSocketTimeout(DefaultConfig.SOCKET_TIMOUT)
                .setConnectTimeout(DefaultConfig.CONNECTION_TIMOUT)
                .build();
        this.httpPost.setConfig(requestConfig);
        try {
            httpResponse = httpClient.execute(this.httpPost);
        } catch (IOException e) {
            throw new RequestsException(ErrorCode.RESULT_ERROR);
        }
        return httpResponse;
    }
}
