package com.example.themagicalbrush.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.themagicalbrush.R;



/**
 * 项目名：    TheMagicalBrush
 * 包名：      com.example.themagicalbrush
 * 文件名：    ChatAdapter
 * 作者：      钟士宜
 * 创建时间    2019/9/16 20:56
 * 描述：      社区Fragment
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater inflater;

    public ChatAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public final String[] imageUrls = new String[]{"https://img-my.csdn.net/uploads/201309/01/1378037235_3453.jpg",
            "https://ae01.alicdn.com/kf/H13c7a9e1dbb54efd895e0f882a1ff0adc.png",
            "https://ae01.alicdn.com/kf/H99a4042a0ad84439ae2aeec4dd4aec8ce.jpg",
            "https://ae01.alicdn.com/kf/H8a9d31b9852b4b4ca178445995f549d0f.jpg",
            "https://ae01.alicdn.com/kf/H26f2cac49e934ad5bc5d2cd5fccb98f1F.jpg",
            "https://ae01.alicdn.com/kf/Hb8aba6083bfa4202855555738d7d306fr.jpg",
            "https://ae01.alicdn.com/kf/H96365266acb244c28e9ad2a196c311cel.jpg",
            "https://ae01.alicdn.com/kf/H3f28f7a2c7884b6dae2f1f61a4114ce00.jpg",
            "https://ae01.alicdn.com/kf/H1d0c06fa3a4b4c198321a9b468783559Z.jpg",
            "https://ae01.alicdn.com/kf/H7d47d73990724b5e9284160d91e9424aq.jpg",
            "https://ae01.alicdn.com/kf/Hd39a6f3dc9104eb0b849cf642ddb327bW.jpg",
            "https://ae01.alicdn.com/kf/H8de14e5fe110421990793fd2f055b21do.png",
            "https://img-my.csdn.net/uploads/201309/01/1378037152_6352.jpg",
            "https://img-my.csdn.net/uploads/201309/01/1378037151_9565.jpg",
            "https://img-my.csdn.net/uploads/201309/01/1378037151_7904.jpg",
            "https://img-my.csdn.net/uploads/201309/01/1378037148_7104.jpg",
            "https://img-my.csdn.net/uploads/201309/01/1378037129_8825.jpg",
            "https://img-my.csdn.net/uploads/201309/01/1378037128_5291.jpg",
            "https://img-my.csdn.net/uploads/201309/01/1378037128_3531.jpg",
            "https://img-my.csdn.net/uploads/201309/01/1378037127_1085.jpg",
            "https://img-my.csdn.net/uploads/201309/01/1378037095_7515.jpg",
            "https://img-my.csdn.net/uploads/201309/01/1378037094_8001.jpg",
            "https://img-my.csdn.net/uploads/201309/01/1378037093_7168.jpg",
            "https://img-my.csdn.net/uploads/201309/01/1378037091_4950.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949643_6410.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949642_6939.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949630_4505.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949630_4593.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949629_7309.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949629_8247.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949615_1986.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949614_8482.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949614_3743.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949614_4199.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949599_3416.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949599_5269.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949598_7858.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949598_9982.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949578_2770.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949578_8744.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949577_5210.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949577_1998.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949482_8813.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949481_6577.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949480_4490.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949455_6792.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949455_6345.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949442_4553.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949441_8987.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949441_5454.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949454_6367.jpg",
            "https://img-my.csdn.net/uploads/201308/31/1377949442_4562.jpg"};

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.chat_item, parent, false);//单个item的布局
        return new MyViewHolder(inflate);
    }

    @Override
    public int getItemCount() {
        return imageUrls.length;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.textView.setText("小小白" + position);
        //用了Glide加载图片，这是一款不错的图片加载框架，毕竟属于Google亲儿子了吧，不会用的也可以用picasso，这个框架参考picasso的，基本跟picasso一模一样。
        Glide.with(context)
                .load(imageUrls[position])
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)//这里设置了不做任何缓存
                .placeholder(R.drawable.picture_2)//这是占位图。加载图片的一瞬间可以用来填充，万一网络不行还可以有个好的视觉效果。毕竟什么都不显示让人很绝望呀。。。
                .into(new BitmapImageViewTarget(holder.imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        //这里用来进行图片转换，转成圆形的图。
                        RoundedBitmapDrawable circleBitmap = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circleBitmap.setCircular(false);
                        holder.imageView.setImageDrawable(circleBitmap);
                    }
                });
        //这里就是让我们最终的效果跟Gridview不同的原因了，我们把第一列和第三列的首张图片设置距离顶部一个距离，这样布局错落有致，就是我们要的效果了
        if (position == 0 ) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(holder.imageView.getLayoutParams());
            lp.setMargins(0, 250, 0, 0);
            holder.imageView.setLayoutParams(lp);
        } else {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(holder.imageView.getLayoutParams());
            lp.setMargins(0, 0, 0, 0);
            holder.imageView.setLayoutParams(lp);
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.im_chat_img);
            textView = itemView.findViewById(R.id.tv_author);
        }
    }
}


