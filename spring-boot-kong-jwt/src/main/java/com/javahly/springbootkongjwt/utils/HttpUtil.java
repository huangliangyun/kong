package com.javahly.springbootkongjwt.utils;



import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2019/11/21
 * @QQ :1136513099
 * @desc :
 */
@Slf4j
public class HttpUtil {

    public static String get(String url) throws MalformedURLException, URISyntaxException {

        InputStream in = null;
        InputStreamReader isr = null;
        String result = null;

        URL url1 = new URL(url);
        URI uri = new URI(url1.getProtocol(),url1.getHost(),url1.getPath(),url1.getQuery(),null);
        HttpGet get = new HttpGet(uri);

        CloseableHttpClient client = HttpClientBuilder.create().build();

        try {
            HttpResponse httpResponse = client.execute(get);

            in = httpResponse.getEntity().getContent();
            isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
            result = br.readLine();

            if ((httpResponse.getStatusLine().getStatusCode()) != HttpStatus.SC_OK) {
                get.abort();
                log.error("response status not 200!");
                throw new RuntimeException("http response status abnormal:"
                        + httpResponse.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            get.abort();
            log.error("httpUtil异常：" + e);
        } finally {
            if (isr != null){
                try {
                    isr.close();
                } catch (IOException e) {
                    log.error("httpUtil异常：" + e);
                }
            }
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("httpUtil异常：" + e);
                }
            }
            if(client != null){
                try {
                    client.close();
                } catch (IOException e) {
                    log.error("httpUtil异常：" + e);
                }
            }
        }
        return result;
    }
}
