package example;

import com.ixuning.requests.request.Header;
import com.ixuning.requests.request.RequestClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2016/12/18.
 */
public class demo {
    public static void main(String[] args) throws IOException {
        RequestClient requestClient=new RequestClient();
        String httpResponse=requestClient.openRequest("http://localhost:9200", "GET").getStringResponse();
        System.out.println(httpResponse);

    }
}
