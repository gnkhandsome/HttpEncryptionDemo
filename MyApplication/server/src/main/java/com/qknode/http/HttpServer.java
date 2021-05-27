package com.qknode.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    private boolean mRunning = false;

    // 启动server
    public void startHttpServer(HttpCallback httpCallback) {
        if (mRunning) {
            // 如果在运行，可以返回错误信息
            return;
        }
        mRunning = true;
        // ServerSocket :启动socket
        try {
            ServerSocket serverSocket = new ServerSocket(80);
            while (mRunning) {
                Socket socket = serverSocket.accept();
                System.out.println("accept");
                ExecutorService executorService = Executors.newCachedThreadPool();
                executorService.execute(new HttpThread(socket, httpCallback));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
