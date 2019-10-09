package com.example.themagicalbrush.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.themagicalbrush.MainActivity;
import com.example.themagicalbrush.R;

/**
 * 项目名：    TheMagicalBrush
 * 包名：      com.example.themagicalbrush.ui
 * 文件名：    LoginActivity
 * 作者：      钟士宜
 * 创建时间    2019/10/9 15:55
 * 描述：      登录界面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login;
    private TextView tv_to_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        tv_to_register = findViewById(R.id.tv_to_register);
        tv_to_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.tv_to_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
        }
    }
}
