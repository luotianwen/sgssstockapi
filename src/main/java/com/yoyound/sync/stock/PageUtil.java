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
        HttpClientBuilder custom = HttpClients.custom();//����httpclient
        //ͨ������������һ��httpclient���󣬿�����Ϊ�ǻ�ȡ��һ�����������
        CloseableHttpClient build = custom.build();
        //��url��װ��get������
        HttpGet httpGet = new HttpGet(url);

        try {
            Thread.sleep(1000L);
            //ʹ��clientִ��get����,��ȡ����Ľ��������Ľ������װ��response��
            CloseableHttpResponse response = build.execute(httpGet);
            //��ʾ��ȡ���ص�����ʵ�����
            HttpEntity entity = response.getEntity();

            long contentLength = entity.getContentLength();
            InputStream is = entity.getContent();
            // ����InputStream �����ļ�
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
           // System.out.println("����"+path+"�ɹ�");
            response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}