package cn.networklab.requests;

import cn.networklab.requests.core.RequestImpl;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by xuning on 2017/11/17.
 */
public class ExampleTest {

    private Requests client;

    @Before
    public void init(){
        client = new RequestImpl();
    }

    @Test
    public void testSearchWebSiteInterface(){
        String url = "http://119.254.86.83:3002/ajax/v1/userLog/_list";
        String body = "\n" +
                "{\n" +
                "  \"draw\": 4,\n" +
                "  \"columns\": [\n" +
                "        \t\t{\n" +
                "    \t\t\t  \"data\": \"snp_event_name\",\n" +
                "\t\t\t      \"name\": \"snp_event_name\",\n" +
                "\t\t\t      \"searchable\": true,\n" +
                "\t\t\t      \"orderable\": false,\n" +
                "    \t\t\t  \"search\": {\n" +
                "        \t\t\t\t\t\"value\": \"search\",\n" +
                "        \t\t\t\t\t\"regex\": false\n" +
                "    \t\t\t\t\t\t}\n" +
                "    \t\t\t},        \t\t\n" +
                "    \t\t\t{\n" +
                "    \t\t\t  \"data\": \"snp_event_type\",\n" +
                "\t\t\t      \"name\": \"snp_event_type\",\n" +
                "\t\t\t      \"searchable\": true,\n" +
                "\t\t\t      \"orderable\": false,\n" +
                "    \t\t\t  \"search\": {\n" +
                "        \t\t\t\t\t\"value\": \"\",\n" +
                "        \t\t\t\t\t\"regex\": false\n" +
                "    \t\t\t\t\t\t}\n" +
                "    \t\t\t}\n" +
                "\t\t\t],\n" +
                "  \"order\": [\n" +
                "    {\n" +
                "      \"column\": 1,\n" +
                "      \"dir\": \"asc\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"start\": 1,\n" +
                "  \"length\": 1,\n" +
                "  \"search\": {\n" +
                "    \"value\": \"\",\n" +
                "    \"regex\": false\n" +
                "  }\n" +
                "}\n";
        String result = client.get("http://www.baidu.com").text();
        Map<String, Object> bodyMap = new Gson().fromJson(body, Map.class);
        String results = client.post(url, null, bodyMap, null).json();
        System.out.println(results);
    }
}
