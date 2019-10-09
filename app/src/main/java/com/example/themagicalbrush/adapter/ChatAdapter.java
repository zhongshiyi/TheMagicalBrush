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

    public final String[] imageUrls = new String[]{
            "https://ae01.alicdn.com/kf/H690d06469f124dd58566d5abe6ed4a02y.png",
            "https://ae01.alicdn.com/kf/H343db467578947398615e2b64f44f1b5C.jpg",
            "https://ae01.alicdn.com/kf/H7292b27cad5f4bc293a080b31f9386f4u.jpg",
            "https://ae01.alicdn.com/kf/H4ef5e4e83d9040bdb610892323e07422M.png",
            "https://ae01.alicdn.com/kf/Hbb522e430c5f4dd28b6d4c100425f3b2N.jpg",
            "https://ae01.alicdn.com/kf/H55f952addc454dbfb52170aa0c5b3f4dI.png",
            "https://ae01.alicdn.com/kf/H6f88e8a4dd324e36bc921bd22c1e13d2Z.png",
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
            };

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
        String[] name = {"小小白","王昭君","蜡笔小新","张三","Miles","宝贝",
                "李凯凯","中十一","李浩豪","哈哈","三棵树","八个雅鹿",
                "少先队员","蜡笔小新","蜡笔小新","蜡笔小新","蜡笔小新","蜡笔小新","蜡笔小新"};
        holder.textView.setText(name[position]);
        holder.tv_number.setText("10" + position * position);
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
        private TextView tv_number;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.im_chat_img);
            textView = itemView.findViewById(R.id.tv_author);
            tv_number = itemView.findViewById(R.id.tv_number);
        }
    }
}


