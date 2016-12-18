package com.ixuning.requests.util;

import com.ixuning.requests.bean.exception.RequestsForXNException;
import com.ixuning.requests.request.Header;
import org.apache.http.NameValuePair;

import java.util.ArrayList;

/**
 * Created by xuning on 2016/8/10.
 */

/**
 * 请求Util
 */
public final class RequestUtil {
    private RequestUtil() {
    }

    /**
     * 添加Query参数
     *
     * @param valuePairs
     * @return
     */
    public static final ArrayList<NameValuePair> addToNameValuePairList(NameValuePair... valuePairs) {
        if (valuePairs.length <= 0) {
            throw new RequestsForXNException("query header is null");
        }
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        for (NameValuePair p : valuePairs) {
            nameValuePairs.add(p);
        }
        return nameValuePairs;
    }

    /**
     * 添加Header信息
     *
     * @param headers
     * @return
     */
    public static final ArrayList<Header> addToHeaderList(Header... headers) {
        if (headers.length <= 0) {
            throw new RequestsForXNException("request header is null");
        }
        ArrayList<Header> headerList = new ArrayList<>();
        for (Header header : headers) {
            headerList.add(header);
        }
        return headerList;
    }

}
