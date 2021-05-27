package com.qknode.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qknode.myapplication.http.HttpRequest;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "http://172.16.75.9/";
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btn_show);
        TextView message = findViewById(R.id.tv_text);
        HttpRequest httpRequest = new HttpRequest(URL);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpRequest.request(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.i(TAG, "请求失败 " + e.getMessage());
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        // response.body().string() 这个方法只能使用一次
                        Log.i(TAG, "请求成功 " + response.body().string());
                    }
                });
            }
        });
    }
}