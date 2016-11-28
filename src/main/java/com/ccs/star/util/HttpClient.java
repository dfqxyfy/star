package com.ccs.star.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chaichuanshi on 2016/11/11.
 */

public class HttpClient {


    public static String get(String url, Map<String,Object> paramMap) {

        try {

            URL realurl = new URL(setGetMethodParams(url,paramMap));
            HttpURLConnection conn= (HttpURLConnection) realurl.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            PrintWriter pw = new PrintWriter(conn.getOutputStream());

            conn.getOutputStream().flush();
            conn.disconnect();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str = null;
            StringBuilder strb = new StringBuilder();
            while ((str = br.readLine()) != null) {
                strb.append(str);
            }
            return strb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String post(String href,Map<String,Object> map) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(href);
            String para = postMethodParams(map);
            //1.得到HttpURLConnection实例化对象
            conn = (HttpURLConnection) url.openConnection();
            //2.设置请求方式
            conn.setRequestMethod("POST");

            conn.setRequestProperty("contentType", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(para.getBytes().length));
            //默认为false
            conn.setDoOutput(true);
            //4.向服务器写入数据
            conn.getOutputStream().write(para.getBytes());
            conn.getOutputStream().flush();
            //5.得到服务器相应

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str = null;
            StringBuilder strb = new StringBuilder();
            while ((str = br.readLine()) != null) {
                strb.append(str);
            }
            return strb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            if (conn != null) {
                //关闭连接 即设置 http.keepAlive = false;
                conn.disconnect();
            }
        }
        return null;
    }

    private static String setGetMethodParams(String url, Map<String, Object> params) {
        StringBuffer urlBuffer = new StringBuffer(url);
        try {
            boolean flag = url.contains("?");
            if (params != null)
                for (String key : params.keySet()) {
                    if(key == null || params.get(key) == null)
                        continue;
                    String value = String.valueOf(params.get(key));
                    if (value != null && !"".equals(value)) {
                        if (flag) {
                            urlBuffer.append("&");
                        } else {
                            urlBuffer.append("?");
                            flag = true;
                        }
                        urlBuffer.append(key).append("=").append(URLEncoder.encode(String.valueOf(params.get(key)), "UTF-8"));
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlBuffer.toString();
    }


    private static String postMethodParams(Map<String, Object> params) {
        StringBuffer urlBuffer = new StringBuffer();
        try {
            if (params != null)
                for (String key : params.keySet()) {
                    if(key == null || params.get(key) == null)
                        continue;
                    String value = String.valueOf(params.get(key));
                    if (value != null && !"".equals(value)) {
                        urlBuffer.append("&");
                        urlBuffer.append(key).append("=").append(URLEncoder.encode(params.get(key).toString(), "UTF-8"));
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlBuffer.toString();
    }

    public static void main(String[] args) {

        String url = "http://localhost:8080/star/findStar";
        Map<String,Object> map = new HashMap<>();
        map.put("num",1);
        //String s = get(url,map);
        String s = get(url,map);
        System.out.println(s);
       // post();
    }
}
