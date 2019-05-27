package com.yoyound.sync.stock;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class PageUtil {


    public static void getFile(String url,String path){
        File tmpFile = new File(path);
        if(!tmpFile.getParentFile().exists()){
            tmpFile.getParentFile().mkdirs();
        }
        HttpClientBuilder custom = HttpClients.custom();//创建httpclient
        //通过构建器构建一个httpclient对象，可以认为是获取到一个浏览器对象
        CloseableHttpClient build = custom.build();
        //把url封装到get请求中
        HttpGet httpGet = new HttpGet(url);

        try {
            Thread.sleep(1000L);
            //使用client执行get请求,获取请求的结果，请求的结果被封装到response中
            CloseableHttpResponse response = build.execute(httpGet);
            //表示获取返回的内容实体对象
            HttpEntity entity = response.getEntity();

            long contentLength = entity.getContentLength();
            InputStream is = entity.getContent();
            // 根据InputStream 下载文件
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int r = 0;

            while ((r = is.read(buffer)) > 0) {
                output.write(buffer, 0, r);
            }

            FileOutputStream fos = new FileOutputStream(path);
            output.writeTo(fos);
            output.flush();
            output.close();
            fos.close();
           // System.out.println("下载"+path+"成功");
            response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}