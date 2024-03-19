package com.exam.complier.complier_cplusplus;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;

/**
 * C++编译器
 * @author tpw
 *
 */
@Service
public class CplusplusComplier {

    private final static String CplusplusFILEPATH=System.getProperty("user.dir") + "\\c++Code\\";
    private final static String CplusplusFILENAME="main.cpp";
    private final static String CplusplusEXE="main.exe";

    private String inputData; //输入数据
    private String outputData; //输出数据
    private String errorMsg; //程序错误信息

    public CplusplusComplier() {
        this.inputData="";
        this.errorMsg="";
    }
    public CplusplusComplier(String inputData) {
        super();
        this.inputData = inputData;
        this.errorMsg="";
    }
    public CplusplusComplier(String inputData, String outputData, String errorMsg) {
        super();
        this.inputData = inputData;
        this.outputData = outputData;
        this.errorMsg = errorMsg;
    }

    public void complier() {
        // 进入c++代码存放文件夹
        // 使用g++命令来编译c++文件,来生成一个exe文件
        // 直接运行exe文件

        // cmd命令：cd /d d:\javaworkspace\complier\c++Code
        // cmd命令：g++ main.cpp
        // cmd命令：main.exe
        StringBuffer errorInfo = new StringBuffer();
        Process p=null;
        try {
            //1.编译C++文件
            p = Runtime.getRuntime().exec("g++ "+CplusplusFILEPATH+CplusplusFILENAME+" -o "+CplusplusFILEPATH+"main");
            // 获取进程的错误流
            final InputStream is1 = p.getErrorStream();
            // 开一个线程,读标准错误流
            new Thread() {
                public void run() {
                    try {
                        BufferedReader br1 = new BufferedReader(new InputStreamReader(is1,Charset.forName("GBK")));
                        String line1 = null;
                        while ((line1 = br1.readLine()) != null) {
                            if (line1 != null) {
                                errorInfo.append(line1 + "\n");
                            }
                        }
                        if(!errorInfo.toString().equals("")) {
                            errorMsg=errorInfo.toString();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            is1.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
            p.waitFor();
            p.destroy();
            //2.如果没错的话就运行exe文件
            if (errorInfo.toString().equals("")) {
                try {
                    Process process = Runtime.getRuntime().exec(CplusplusFILEPATH+CplusplusEXE);
                    BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                    bout.write(this.inputData);
                    bout.close();
                    BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(),Charset.forName("GBK")));
                    String line = null;
                    StringBuffer b = new StringBuffer();
                    while ((line = br.readLine()) != null) {
                        b.append(line + "\n");
                    }
                    this.outputData=b.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            try {
                p.getErrorStream().close();
                p.getInputStream().close();
                p.getOutputStream().close();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    public String getInputData() {
        return inputData;
    }
    public void setInputData(String inputData) {
        this.inputData = inputData;
    }
    public String getOutputData() {
        return outputData;
    }
    public void setOutputData(String outputData) {
        this.outputData = outputData;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
