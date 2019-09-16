package com.example.themagicalbrush.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.themagicalbrush.R;
import com.example.themagicalbrush.entity.ChatData;

import org.w3c.dom.Text;

import java.util.List;

/**
 * 项目名：    TheMagicalBrush
 * 包名：      com.example.themagicalbrush
 * 文件名：    ChatAdapter
 * 作者：      钟士宜
 * 创建时间    2019/9/16 20:56
 * 描述：      TODO
 */
public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //数据源
    private List<ChatData> mList;

    public ChatAdapter(List<ChatData>mList){
        this.mList = mList;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_item,viewGroup,false);
        RecyclerView.ViewHolder holder = new MyViewHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        ChatData data = mList.get(i);
        holder.iv_chat_img.setImageResource(R.drawable.picture_2);
        holder.tv_author.setText(data.getAuthor());

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    //创建ViewHolder类
    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_chat_img;
        private TextView tv_author;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_chat_img = itemView.findViewById(R.id.im_chat_img);
            tv_author = itemView.findViewById(R.id.tv_author);
        }
    }

}
