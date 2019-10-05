package com.example.themagicalbrush.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.themagicalbrush.GridSpacingItemDecoration;
import com.example.themagicalbrush.R;

import com.example.themagicalbrush.adapter.ChatAdapter;
import com.example.themagicalbrush.entity.ChatData;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：    TheMagicalBrush
 * 包名：      com.example.themagicalbrush.fragment
 * 文件名：    ChatFragment
 * 作者：      钟士宜
 * 创建时间    2019/9/13 10:09
 * 描述：      社区Fragment
 */
public class ChatFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat,container,false);
        recyclerView = view.findViewById(R.id.recycler_view);


        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);//定义瀑布流管理器，第一个参数是列数，第二个是方向。
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//不设置的话，图片闪烁错位，有可能有整列错位的情况。
        recyclerView.setLayoutManager(layoutManager);//设置瀑布流管理器
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,0,true));//边距和分割线，需要自己定义
        recyclerView.setAdapter(new ChatAdapter(getContext()));//设置适配器
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();//这行主要解决了当加载更多数据时，底部需要重绘，否则布局可能衔接不上。
            }
        });

        return view;
    }

}
