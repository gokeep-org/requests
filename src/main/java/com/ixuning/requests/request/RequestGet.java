package com.ixuning.requests.request;

import com.ixuning.requests.bean.exception.RequestsForXNException;
import com.ixuning.requests.core.Config;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Created by xuning on 2016/8/10.
 */
public class RequestGet extends RequestOperation {
    private String url;
    private List<Header> headers;

    public RequestGet(RequestClient requestClient) {
        this.url = requestClient.getUrl();
        this.headers = requestClient.getHeaders();
    }

    @Override
    protected HttpResponse oper() {
        HttpResponse httpResponse;
        HttpGet httpGet = new HttpGet(this.url);
        if (!Objects.equals( null,this.headers)&&this.headers.size() > 0 ) {
            for (Header header : this.headers) {
                httpGet.setHeader(header.getKey(), header.getValue());
            }
        }
        RequestConfig requestConfig = RequestConfig
                .custom()
                .setSocketTimeout(Config.DEFAULT_SOCKET_TIMOUT_CHARSET)
                .setConnectTimeout(Config.DEFAULT_CONNECTION_TIMOUT)
                .build();
        httpGet.setConfig(requestConfig);
        try {
            httpResponse = httpClient.execute(httpGet);
            if (Objects.equals(null, httpResponse)){
                new RequestsForXNException("httpresponse is null");
            }
        }
        catch (IOException e) {
            throw new RequestsForXNException(e.getMessage());

        }
        return httpResponse;
    }
}
