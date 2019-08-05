package com.demo.lyl.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 文件下载工具类
 */
public class LocalFilesDownload {
    HttpServletResponse response = null;
    /**
     * 下载本地文件,并按文件名进行排序
     */
    public void download(){
        try {
            String fileName = "";
            String path = "";
            boolean isExits = false;
            File tempFile = new File(path);
            File[] files = tempFile.listFiles();
            List<File> fileList = Arrays.asList(files);
            Collections.sort(fileList, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    if (o1.isDirectory() && o2.isFile()) {
                        return -1;
                    }
                    if (o1.isFile() && o2.isDirectory()) {
                        return 1;
                    }
                    return o1.getName().compareTo(o2.getName());
                }
            });
            File outFile = null;
            for (File file:fileList) {
                if (file.getName().startsWith("")){
                    isExits = true;
                }
            }
            if(isExits){
                fileName = "";
                outFile = new File(fileName);
            }else{
                fileName = fileList.get(fileList.size() - 1).getName();
                outFile = new File(fileName);
            }
            InputStream inputStream = new FileInputStream(outFile);
            OutputStream out = response.getOutputStream();
            response.reset();
            response.setContentType("Application/vnd.ms-excel");
            response.setHeader("Content-Disposition","attachment;filename" + fileName);
            byte[] buffer = new byte[4096];
            int len;
            while((len = inputStream.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            inputStream.close();
            out.close();
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
