package cn.networklab.requests.core;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.networklab.requests.exception.ErrorCode;
import cn.networklab.requests.exception.RequestsException;
import cn.networklab.requests.filter.RequestFilter;
import cn.networklab.requests.util.RequestUtil;

public class RequestGet extends cn.networklab.requests.core.RequestClient<HttpGet> {
    private HttpResponse httpResponse = null;
    private HttpGet httpGet = new HttpGet();
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestGet.class);

    @Override
    protected HttpGet setUpHttpMethod() {
        return this.httpGet;
    }

    @Override
    protected HttpResponse sendRequest(String url, Map<String, String> params, Map<String, String> postForms, Object body, Map<String, String> headers) {

        if (!RequestFilter.checkHeaderisNull(headers)) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                httpGet.setHeader(header.getKey(), header.getValue());
            }
        }
        httpGet.setURI(RequestUtil.getURI(url));
        if (!RequestFilter.checkParamsIsNull(params)) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                LOGGER.debug("key: " + param.getKey() + "value: " + param.getValue());
            }
            this.httpGet.setURI(
                    RequestUtil.getURI(url + RequestUtil.buildParames(params))
            );
        }

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setSocketTimeout(cn.networklab.requests.core.DefaultConfig.SOCKET_TIMOUT)
                .setConnectTimeout(cn.networklab.requests.core.DefaultConfig.CONNECTION_TIMOUT)
                .build();
        this.httpGet.setConfig(requestConfig);
        try {
            httpResponse = httpClient.execute(this.httpGet);
        } catch (IOException e) {
            throw new RequestsException(ErrorCode.RESULT_ERROR);
        }
        return httpResponse;
    }
}
