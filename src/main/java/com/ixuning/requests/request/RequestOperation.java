package com.ixuning.requests.request;

import com.ixuning.requests.bean.exception.ExternalErrorCode;
import com.ixuning.requests.bean.exception.RequestsForXNException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.Objects;

/**
 * Created by xuning on 2016/8/10.
 */
public abstract class RequestOperation {
    protected HttpClient httpClient = null;

    protected void openHttpClient() {
        this.httpClient = HttpClients.createDefault();
    }

    protected abstract HttpResponse oper();

    protected void closeHttpClient() {
        if (!Objects.equals(null, httpClient)) {
            try {
                httpClient.getConnectionManager().shutdown();
            }
            catch (Exception e) {
                throw new RequestsForXNException(ExternalErrorCode.HTTP_CLIENT_CLOSE_EXCEPTION);
            }
        }
    }

    public HttpResponse execute() {
        openHttpClient();
        HttpResponse httpResponse = oper();
//        closeHttpClient();
        return httpResponse;
    }

}
