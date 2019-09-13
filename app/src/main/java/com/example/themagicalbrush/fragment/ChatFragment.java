package com.example.themagicalbrush.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.themagicalbrush.R;

/**
 * 项目名：    TheMagicalBrush
 * 包名：      com.example.themagicalbrush.fragment
 * 文件名：    ChatFragment
 * 作者：      钟士宜
 * 创建时间    2019/9/13 10:09
 * 描述：      社区Fragment
 */
public class ChatFragment extends Fragment {

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat,container,false);


        return view;
    }

}
