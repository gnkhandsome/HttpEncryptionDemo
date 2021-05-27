package com.qknode.server;

import com.qknode.http.HttpCallback;
import com.qknode.http.HttpServer;

public class Main {

    public static void main(String[] args) {
        System.out.println("start http server");
        HttpServer httpServer = new HttpServer();
        httpServer.startHttpServer(new HttpCallback() {
            @Override
            public byte[] onResponse(String request) {
                return "sgssgsg".getBytes();
            }
        });
    }
}
