package com.example.themagicalbrush.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.themagicalbrush.R;

/**
 * 项目名：    TheMagicalBrush
 * 包名：      com.example.themagicalbrush.adapter
 * 文件名：    MemoryAdapter
 * 作者：      钟士宜
 * 创建时间    2019/10/5 20:43
 * 描述：      TODO
 */
public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater inflater;

    public MemoryAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public final String[] imageUrls = new String[]{
            "https://ae01.alicdn.com/kf/H7292b27cad5f4bc293a080b31f9386f4u.jpg",
            "https://ae01.alicdn.com/kf/Hbb522e430c5f4dd28b6d4c100425f3b2N.jpg",
            "https://ae01.alicdn.com/kf/H55f952addc454dbfb52170aa0c5b3f4dI.png",
            "https://ae01.alicdn.com/kf/H6f88e8a4dd324e36bc921bd22c1e13d2Z.png",
            "https://ae01.alicdn.com/kf/H4ef5e4e83d9040bdb610892323e07422M.png",
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
            "https://ae01.alicdn.com/kf/H8de14e5fe110421990793fd2f055b21do.png",};

    @Override
    public MemoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.memory_item,parent,false);
        return new MemoryAdapter.MyViewHolder(inflate);
    }

    @Override
    public int getItemCount() {
        return imageUrls.length;
    }

    @Override
    public void onBindViewHolder(final MemoryAdapter.MyViewHolder holder, int position) {
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
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_memory_item);
        }
    }

}
