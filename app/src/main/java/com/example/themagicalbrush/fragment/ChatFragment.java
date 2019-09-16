package com.example.themagicalbrush.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    List<ChatData> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat,container,false);
        recyclerView = view.findViewById(R.id.recycler_view);
        GridLayoutManager manager = new GridLayoutManager(container.getContext(),2);
        recyclerView.setLayoutManager(manager);

        for (int i = 0;i < 10; i ++){
            list.add(new ChatData());
        }

        ChatAdapter adapter = new ChatAdapter(list);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
