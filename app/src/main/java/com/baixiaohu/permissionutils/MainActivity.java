package com.baixiaohu.permissionutils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.baixiaohu.permission.PermissionActivity;
import com.baixiaohu.permission.imp.OnPermissionsResult;

import java.util.List;

public class MainActivity extends PermissionActivity {

    @Override
    protected void onResume() {
        super.onResume();
        requestPermission(new OnPermissionsResult() {
            @Override
            public void onAllow(List<String> permissions) {
                Log.w("requestPermission--","全部同意");
            }

            @Override
            public void onNoAllow(List<String> permissions) {
                Log.w("requestPermission--","全部不同意");
            }

            @Override
            public void onForbid(List<String> permissions) {
                Log.w("requestPermission--","有被禁止");
            }

            @Override
            public void onLowVersion() {
                Log.w("requestPermission--","我是低版本");
            }
        }, new String[]{
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
                Manifest.permission.SEND_SMS
        });
    }

    @SuppressLint("InlinedApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
