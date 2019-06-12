package com.example.lenovo.qrcode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.mButton);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检测相机权限
                if (!checkCameraPersion()) {
                    //如果不允许
                    //我们就要请求相机权限
                    requestCameraPermission();
                } else {
                    doScan();//开启摄像头扫描
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取扫描结果
        //判断请求码
        if (requestCode == 101) {
            //判断数据是否为空
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    //获取最终扫描数据
                    String result = bundle.getString(CodeUtils.RESULT_STRING);

                    Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(MainActivity.this,WedActivity.class);
                    intent.putExtra("name",result);
                    startActivity(intent);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(this, "错误", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "数据为空", Toast.LENGTH_LONG).show();

            }
        }
    }

    /**
     * 检测相机权限
     *
     * @return true 允许 false 不允许
     */
    private boolean checkCameraPersion() {
        //返回当前应用是否允许使用Manifest.permission.CAMERA权限
        return PermissionChecker.checkSelfPermission(this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 请求相机权限
     */
    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                .CAMERA}, 100);
    }

    /**
     * 开启扫描
     */
    private void doScan() {
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent, 101);
    }

}


