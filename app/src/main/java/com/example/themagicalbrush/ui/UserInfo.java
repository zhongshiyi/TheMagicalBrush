package com.example.themagicalbrush.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.themagicalbrush.MainActivity;
import com.example.themagicalbrush.R;
import com.example.themagicalbrush.fragment.UserFragment;

/**
 * 项目名：    TheMagicalBrush
 * 包名：      com.example.themagicalbrush.ui
 * 文件名：    UserInfo
 * 作者：      钟士宜
 * 创建时间    2019/10/5 15:18
 * 描述：      个人资料
 */
public class UserInfo extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_left_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        //初始化
        initView();
    }

    private void initView(){
        iv_left_back = findViewById(R.id.iv_left_back);
        iv_left_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_left_back:
                startActivity(new Intent(UserInfo.this,MainActivity.class));
                break;

        }
    }
}
