package cn.networklab.requests.core;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.GsonBuilder;

import cn.networklab.requests.bean.HttpDelete;
import cn.networklab.requests.exception.ErrorCode;
import cn.networklab.requests.exception.RequestsException;
import cn.networklab.requests.filter.RequestFilter;
import cn.networklab.requests.util.RequestUtil;


public class RequestDelete extends cn.networklab.requests.core.RequestClient<HttpDelete> {
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
                .setSocketTimeout(cn.networklab.requests.core.DefaultConfig.SOCKET_TIMOUT)
                .setConnectTimeout(cn.networklab.requests.core.DefaultConfig.CONNECTION_TIMOUT)
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
