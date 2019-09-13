package com.example.themagicalbrush.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.themagicalbrush.R;
import com.example.themagicalbrush.ui.Chat;
import com.example.themagicalbrush.ui.Discuss;
import com.example.themagicalbrush.ui.Inform;
import com.example.themagicalbrush.ui.MyLike;

/**
 * 项目名：    TheMagicalBrush
 * 包名：      com.example.themagicalbrush.fragment
 * 文件名：    UserFragment
 * 作者：      钟士宜
 * 创建时间    2019/9/13 10:08
 * 描述：      个人中心
 */

public class UserFragment extends Fragment implements View.OnClickListener {

    private LinearLayout ll_like;
    private LinearLayout ll_discuss;
    private LinearLayout ll_chat;
    private LinearLayout ll_inform;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user,container,false);

        findView(view);

        return view;
    }

    //初始化
    private void findView(View view){
        ll_like = view.findViewById(R.id.ll_like);
        ll_like.setOnClickListener(this);
        ll_discuss = view.findViewById(R.id.ll_discuss);
        ll_discuss.setOnClickListener(this);
        ll_chat = view.findViewById(R.id.ll_chat);
        ll_chat.setOnClickListener(this);
        ll_inform = view.findViewById(R.id.ll_inform);
        ll_inform.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ll_like:
                startActivity(new Intent(getActivity(),MyLike.class));
                break;
            case R.id.ll_discuss:
                startActivity(new Intent(getActivity(),Discuss.class));
                break;
            case R.id.ll_chat:
                startActivity(new Intent(getActivity(),Chat.class));
                break;
            case R.id.ll_inform:
                startActivity(new Intent(getActivity(),Inform.class));
                break;
        }
    }
}

