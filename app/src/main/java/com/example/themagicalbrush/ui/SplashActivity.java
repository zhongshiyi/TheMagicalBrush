package com.example.themagicalbrush.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.themagicalbrush.MainActivity;
import com.example.themagicalbrush.R;
import com.example.themagicalbrush.utils.ShareUtils;
import com.example.themagicalbrush.utils.UtilTools;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * 项目名：    TheMagicalBrush
 * 包名：      com.example.themagicalbrush.ui
 * 文件名：    SplashActivity
 * 作者：      钟士宜
 * 创建时间    2019/10/3 21:30
 * 描述：      闪屏类
 */
public class SplashActivity extends AppCompatActivity {
    /**
     * 1、延时2000ms
     * 2、判断程序是不是第一次运行
     * 3、自定义字体
     * 4、Activity全屏主题
     */
    private TextView tv_splash;

    @SuppressLint("HandlerLeak")
    private android.os.Handler handler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1001:
                    //判断程序是不是第一次运行
                    if(isFirst()){
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    }else{
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
    }
    //初始化View
    private void initView() {
        //延时2000ms
        handler.sendEmptyMessageDelayed(1001,1000);

        tv_splash = findViewById(R.id.tv_splash);
        //设置字体
        UtilTools.setFont(this,tv_splash);
    }

    //判断程序是不是第一运行
    private boolean isFirst(){
        boolean isFirst = ShareUtils.getBoolean(this,"isFirst",true);
        if(isFirst){
            ShareUtils.putBoolean(this,"isFirst",false);//变为false
            //是第一次运行
            return true;
        }else{
            return false;
        }
    }
}
