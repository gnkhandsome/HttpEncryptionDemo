package com.qknode.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class HttpThread implements Runnable {

    // 一个socket对应一个客户端
    private Socket mSocket;
    private HttpCallback mHttpCallback;

    public HttpThread(Socket socket, HttpCallback httpCallback) {
        mSocket = socket;
        mHttpCallback = httpCallback;
    }

    @Override
    public void run() {
        // 任务 1.读取客户端请求； 2.根据业务数据采取相应操作；3.返回数据
        // 1. 提升I/O效率， 2.便于逐行读入
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            String content;
            StringBuilder request = new StringBuilder();
            // 完成客户端请求内容逐行读入
            while ((content = bufferedReader.readLine()) != null && !content.trim().isEmpty()) {
                request.append(content).append("\n");
            }

            System.out.println("request:\n" + request);

            // todo 业务数据处理

            //返回数据
            byte[] response = new byte[0];
            if (null != mHttpCallback) {
                response = mHttpCallback.onResponse(request.toString());
            }
            // 将响应头发给客户端
            // 1.拼接请求行
            String responseFirsLine = "HTTP/1.1 200 OK \r\n";
            String responseHead = "Content-type:" + "text/html" + "\r\n";

            OutputStream outputStream = mSocket.getOutputStream();
            outputStream.write(responseFirsLine.getBytes());
            outputStream.write(responseHead.getBytes());
            outputStream.write("\r\n".getBytes());
            outputStream.write(response);
            mSocket.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
