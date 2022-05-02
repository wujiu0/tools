package org.example.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HttpClientExample {

    // one instance, reuse
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public void close() throws IOException {
        httpClient.close();
    }

    public void sendGet() throws Exception {

        HttpGet request = new HttpGet("https://www.google.com/search?q=wujiu");

        // add request headers

        request.setHeader("Cookie", "PHPSESSID=6l1mh4j0kd3ku6f8m7lr8te2fe");
        request.addHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Linux; Android 11; M2105K81AC Build/RKQ1.200826.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/96.0.4664.45 Safari/537.36");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            // return it as a String
            String result = EntityUtils.toString(entity);
            System.out.println(result);

        }

    }

    public void sendPost() throws Exception {

        HttpPost post = new HttpPost("http://yx.ty-ke.com/Home/Monitor/monitor_add");

        post.setHeader("Cookie", "PHPSESSID=6l1mh4j0kd3ku6f8m7lr8te2fe");
        post.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 11; M2105K81AC Build/RKQ1.200826.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/96.0.4664.45 Safari/537.36");
        post.setHeader("Charset", "UTF-8");
        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("mobile", "142731200209044517"));
        urlParameters.add(new BasicNameValuePair("title", "36.5"));
        urlParameters.add(new BasicNameValuePair("jk_type", "健康"));
        urlParameters.add(new BasicNameValuePair("wc_type", "否"));
        urlParameters.add(new BasicNameValuePair("jc_type", "否"));
        urlParameters.add(new BasicNameValuePair("province", "山西省"));
        urlParameters.add(new BasicNameValuePair("city", "太原市"));
        urlParameters.add(new BasicNameValuePair("district", "尖草坪区"));
        urlParameters.add(new BasicNameValuePair("address", "山西省太原市尖草坪区X256"));
        urlParameters.add(new BasicNameValuePair("is_verify", "0"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            System.out.println(EntityUtils.toString(response.getEntity()));
        }


    }

    private static final String[] sessionIds = {"1725atcs7ou90faq1v11i9mun6","6l1mh4j0kd3ku6f8m7lr8te2fe"};
    /***　　sendUrl    （远程请求的URL）*　　param    （远程请求参数）*/
    public static String sendPostUrl(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
//            设置请求头
            String sessionId = sessionIds[new Random().nextInt(2)];
            conn.setRequestProperty("Cookie", "PHPSESSID=1725atcs7ou90faq1v11i9mun6");
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 10; V1950A Build/QP1A.190711.020; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/83.0.4103.106 Mobile Safari/537.36");
            conn.setRequestProperty("Connection", "close");
            conn.setRequestProperty("Charset", "UTF-8");
            // 获取URLConnection对象对应的输出流（设置请求编码为UTF-8）
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8));
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 获取请求返回数据（设置返回数据编码为UTF-8）
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


}
