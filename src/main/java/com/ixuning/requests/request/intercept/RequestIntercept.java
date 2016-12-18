package com.ixuning.requests.request.intercept;

import com.ixuning.requests.bean.exception.ExternalErrorCode;
import com.ixuning.requests.bean.exception.RequestsForXNException;
import com.ixuning.requests.util.log.Logger;
import com.ixuning.requests.util.log.LoggerFactory;
import org.apache.http.HttpResponse;

/**
 * Created by xuning on 2016/8/22.
 */
public class RequestIntercept {
    private static int sendRes = 0;
    private static String requestId;
    private static String code;
    private static String msg;
    private static Logger logger = LoggerFactory.getLogger(RequestIntercept.class);

    public static void ErrorInfoIntercept(HttpResponse httpResponse) {
        sendRes = httpResponse.getStatusLine().getStatusCode();
        String errorLog="response error status code is "+sendRes;
        switch (sendRes) {
        case 0:
            logger.error(errorLog);
            throw new RequestsForXNException(ExternalErrorCode.REQUEST_NO_RESPONSE);
        case 500:
            logger.error(errorLog);
            throw new RequestsForXNException(ExternalErrorCode.SERVER_ERROR);
        case 401:
            logger.info(errorLog);
            break;
        case 200:
            break;
        default:
            logger.error(errorLog);
            throw new RequestsForXNException(code);
        }
    }
}
