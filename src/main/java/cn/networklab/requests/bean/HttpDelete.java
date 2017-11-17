package cn.networklab.requests.bean;

import java.net.URI;

import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

@NotThreadSafe
public class HttpDelete extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";

    public HttpDelete() {
    }

    public HttpDelete(URI uri) {
        this.setURI(uri);
    }

    public HttpDelete(String uri) {
        this.setURI(URI.create(uri));
    }

    public String getMethod() {
        return METHOD_NAME;
    }
}
