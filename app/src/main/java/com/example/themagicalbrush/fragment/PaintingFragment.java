package com.example.themagicalbrush.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.themagicalbrush.R;
import com.example.themagicalbrush.ui.DrawingBoard;
import com.example.themagicalbrush.ui.Memory;
import com.example.themagicalbrush.utils.UtilTools;

/**
 * 项目名：    TheMagicalBrush
 * 包名：      com.example.themagicalbrush.fragment
 * 文件名：    PatintingFragment
 * 作者：      钟士宜
 * 创建时间    2019/9/13 10:08
 * 描述：      绘画主页板
 */
public class PaintingFragment extends Fragment implements View.OnClickListener {

    private ImageView iv_memory;
    private ImageView iv_painting;
    private ImageView iv_other;

    private TextView tv_app_name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_painting, container, false);

        findView(view);

        return view;

    }
    //初始化View
    private void findView(View view){
        iv_memory = view.findViewById(R.id.iv_memory);
        iv_memory.setOnClickListener(this);
        iv_painting = view.findViewById(R.id.iv_painting);
        iv_painting.setOnClickListener(this);
        iv_other = view.findViewById(R.id.iv_other);
        iv_other.setOnClickListener(this);

        tv_app_name = view.findViewById(R.id.tv_app_name);
        //设置字体
        UtilTools.setFont(getActivity(),tv_app_name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_memory:
                startActivity(new Intent(getActivity(),Memory.class));
                break;
            case R.id.iv_painting:
                startActivity(new Intent(getActivity(), DrawingBoard.class));
                break;
        }
    }
}


