package com.baixiaohu.permission;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.baixiaohu.permission.imp.OnPermissionsResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 项  目 :  PermissionUtils
 * 包  名 :  com.baixiaohu.permissionutils
 * 类  名 :  PermissionActivity
 * 作  者 :  胡庆岭
 * 时  间 :  2018/1/11 0011 下午 12:05
 * 描  述 :  ${TODO}
 *
 * @author ：
 */

public class PermissionActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;
    private static List<String> mAllowList = new ArrayList<>();
    private static List<String> mNoAllowList = new ArrayList<>();
    private static List<String> mForbidList = new ArrayList<>();
    private OnPermissionsResult mOnPermissionsResult;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    protected void requestPermission(@NonNull OnPermissionsResult onPermissionsResult, @NonNull String... permissions) {
        this.mOnPermissionsResult = onPermissionsResult;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
            } else {
                mAllowList.clear();
                mOnPermissionsResult.onAllow(Arrays.asList(permissions));
            }
        } else {
            mOnPermissionsResult.onLowVersion();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (permissions.length == grantResults.length) {
                    clearPermission();

                    for (int i = 0; i < grantResults.length; i++) {

                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            mAllowList.add(permissions[i]);
                        } else {
                            Log.w("onRequemt--",ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])+"");
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                                mNoAllowList.add(permissions[i]);
                            } else {
                                mForbidList.add(permissions[i]);
                            }
                        }
                    }
                    Log.w("onRequest--", permissions.length + "--" + grantResults.length
                            + "--" + mAllowList.size() + "--" + mNoAllowList.size() + "--" + mForbidList.size());
                    if (mAllowList.size() == permissions.length) {
                        if (mOnPermissionsResult != null) {
                            mOnPermissionsResult.onAllow(mAllowList);
                        }
                        //全部同意
                    } else if (permissions.length - mAllowList.size() == mNoAllowList.size()) {
                        if (mOnPermissionsResult != null) {
                            mOnPermissionsResult.onNoAllow(mNoAllowList);
                        }
                        //全部拒绝
                    } else {
                        if (mOnPermissionsResult != null) {
                            //全部永久禁止或者部分永久禁止
                            mOnPermissionsResult.onForbid(mForbidList);
                        }
                    }

                }
                break;
            default:
                break;
        }
    }

    private void clearPermission() {
        mAllowList.clear();
        mNoAllowList.clear();
        mForbidList.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearPermission();
    }
}
