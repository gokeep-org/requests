package cn.networklab.requests.bean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import cn.networklab.requests.bean.data.DataType;
import cn.networklab.requests.exception.ErrorCode;
import cn.networklab.requests.exception.RequestsException;


public class HttpResponse implements DataType {
    private org.apache.http.HttpResponse httpResponse;
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpResponse.class);
    private static final int BUFFER_SIZE = 1024;

    public HttpResponse() {
    }

    public org.apache.http.HttpResponse getHttpResponse() {
        return httpResponse;
    }

    public HttpResponse setHttpResponse(org.apache.http.HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
        return this;
    }

    @Override
    public String json() {
        String str = getHttpResponseStr();
        return str;
    }

    @Override
    public String text() {
        return getHttpResponseStr();
    }

    //暂不实现
    @Override
    public InputStream inputStream() {
        return null;
    }

    @Override
    public int statusCode() {
        return httpResponse.getStatusLine().getStatusCode();
    }

    @Override
    public Object toObject(Class classes) {
        return new Gson().fromJson(json(), classes);
    }

    private String getHttpResponseStr() {
        String strRes = falg;
        InputStream inputStream = null;
        try {
            strRes = InputStreamTOString(httpResponse.getEntity().getContent(), "utf-8");
            if (strRes.equals(falg)) {
                throw new RequestsException(ErrorCode.RESULT_TO_STR_IS_ERROR);
            }
            return strRes;
        } catch (IOException e) {
            LOGGER.error("close http response input stream is error");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!Objects.equals(null, inputStream)) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream is error");
                }
            }
        }
        return strRes;
    }

    public static String InputStreamTOString(InputStream inputStream, String encoding) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = inputStream.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);
        data = null;
        String res = new String(outStream.toByteArray(), encoding);
        inputStream.close();
        return res;
    }
}