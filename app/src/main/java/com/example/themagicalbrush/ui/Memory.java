package com.example.themagicalbrush.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.themagicalbrush.GridSpacingItemDecoration;
import com.example.themagicalbrush.MainActivity;
import com.example.themagicalbrush.R;
import com.example.themagicalbrush.adapter.ChatAdapter;
import com.example.themagicalbrush.adapter.MemoryAdapter;
import com.example.themagicalbrush.utils.UtilTools;

/**
 * 项目名：    TheMagicalBrush
 * 包名：      com.example.themagicalbrush.ui
 * 文件名：    Memory
 * 作者：      钟士宜
 * 创建时间    2019/9/13 15:13
 * 描述：      画板记录
 */
public class Memory extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_memory;
    private  RecyclerView recyclerView;
    private ImageView iv_left_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        initView();
    }

    private void initView() {
        tv_memory = findViewById(R.id.tv_memory);
        recyclerView = findViewById(R.id.memory_recycler_view);
        iv_left_back = findViewById(R.id.iv_left_back_2);
        iv_left_back.setOnClickListener(this);


        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);//定义瀑布流管理器，第一个参数是列数，第二个是方向。
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//不设置的话，图片闪烁错位，有可能有整列错位的情况。
        recyclerView.setLayoutManager(layoutManager);//设置瀑布流管理器
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1,40,true));//边距和分割线，需要自己定义
        recyclerView.setAdapter(new MemoryAdapter(this));//设置适配器
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();//这行主要解决了当加载更多数据时，底部需要重绘，否则布局可能衔接不上。
            }
        });

        //设置字体
        UtilTools.setFont(this,tv_memory);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_left_back_2:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }
}
