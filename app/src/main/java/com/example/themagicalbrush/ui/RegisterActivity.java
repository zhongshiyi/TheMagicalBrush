package com.example.themagicalbrush.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.themagicalbrush.R;

/**
 * 项目名：    TheMagicalBrush
 * 包名：      com.example.themagicalbrush.ui
 * 文件名：    RegisterActivity
 * 作者：      钟士宜
 * 创建时间    2019/10/9 15:56
 * 描述：      注册界面
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_to_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        tv_to_login = findViewById(R.id.tv_to_login);
        tv_to_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_to_login:
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
    }
}
