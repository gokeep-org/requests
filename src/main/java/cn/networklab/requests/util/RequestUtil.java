package cn.networklab.requests.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import cn.networklab.requests.core.DefaultConfig;


public class RequestUtil {
    private RequestUtil() {
    }

    public static String buildParames(Map<String, String> params) {
        String res = "?";
        for (Map.Entry<String, String> param : params.entrySet()) {
            res += (param.getKey() + "=" + param.getValue() + "&");
        }
        return res.substring(0, res.length() - 1);
    }

    public static URI getURI(String uriStr) {
        if (!uriStr.contains("https://")) {
            if ((!uriStr.contains("http://"))) {
                uriStr = "http://" + uriStr;
            }
        }
        if (null != uriStr) {
            try {
                return new URI(uriStr);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static HttpEntity toHttpEntity(Map<String, String> postForms) throws UnsupportedEncodingException {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (Map.Entry<String, String> postForm : postForms.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(postForm.getKey(), postForm.getValue()));
        }
        return new UrlEncodedFormEntity(nameValuePairs, DefaultConfig.CHARSET);
    }

    public static StringEntity toStringEntity(String jsonBody) {
        StringEntity stringEntity = new StringEntity(jsonBody, DefaultConfig.DEFAULT_CHARSET_TYPE);
        stringEntity.setContentType(DefaultConfig.CONTENT_TYPE);
        return stringEntity;
    }
}
