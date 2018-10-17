package com.baixiaohu.permissionutils;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baixiaohu.permission.PermissionActivity;
import com.baixiaohu.permission.imp.OnPermissionsResult;

import java.util.List;

public class MainActivity extends PermissionActivity {

    private Button mBtnRP;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mBtnRP = findViewById(R.id.btn_request_permission);
        mBtnRP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
    }

    private void requestPermission() {
        requestPermission(new OnPermissionsResult() {
                              @Override
                              public void onAllow(List<String> permissions) {
                                  Toast.makeText(MainActivity.this,"申请权限成功!",Toast.LENGTH_SHORT).show();
                              }

                              @Override
                              public void onNoAllow(List<String> permissions) {
                                  Toast.makeText(MainActivity.this,"部分权限申请失败，请重新申请",Toast.LENGTH_SHORT).show();
                              }

                              @Override
                              public void onForbid(List<String> permissions) {
                                  showForbidPermissionDialog();

                              }

                          },
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_SMS,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS);
    }
}
