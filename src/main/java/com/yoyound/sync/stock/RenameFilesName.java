package com.yoyound.sync.stock;

import java.io.File;
import java.net.URLDecoder;

public class RenameFilesName implements Runnable {


    @Override
    public void run() {
       traverseFolder2("D:\\goods1\\userfiles");
    }

    public void traverseFolder2(String path) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder2(file2.getAbsolutePath());
                    } else {
                        String path2=file2.getAbsolutePath();
                        System.out.println("文件:" + file2.getAbsolutePath());
                        if(path2.contains("%")) {
                            File  q1= new File(URLDecoder.decode(file2.getAbsolutePath()));
                            if(!q1.getParentFile().exists()){
                                q1.getParentFile().mkdirs();
                            }
                            System.out.println(file2.renameTo(q1)+"文件:" + URLDecoder.decode(file2.getAbsolutePath()));

                        }

                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
}
