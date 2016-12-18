package com.ixuning.requests.request.factory;

import com.ixuning.requests.bean.exception.ExternalErrorCode;
import com.ixuning.requests.bean.exception.RequestsForXNException;
import com.ixuning.requests.core.Config;
import com.ixuning.requests.request.RequestClient;
import com.ixuning.requests.request.RequestDelete;
import com.ixuning.requests.request.RequestGet;
import com.ixuning.requests.request.RequestOperation;
import com.ixuning.requests.request.RequestPost;
import com.ixuning.requests.request.RequestPut;

/**
 * Created by xuning on 2016/9/2.
 */
public class RequestFactory {
    public static RequestOperation getRequestMethod(RequestClient requestClient){
        RequestOperation requestOperation=null;
        switch (requestClient.getMethod().toLowerCase()) {
        case Config.METHOD_GET:
            requestOperation = new RequestGet(requestClient);
            break;
        case Config.METHOD_POST:
            requestOperation = new RequestPost(requestClient);
            break;
        case Config.METHOD_PUT:
            requestOperation = new RequestPut(requestClient);
            break;
        case Config.METHOD_DELETE:
            requestOperation = new RequestDelete(requestClient);
            break;
        default:
            throw new RequestsForXNException(ExternalErrorCode.REQUEST_METHOD_ERROR);
        }
        return requestOperation;
    }
}
