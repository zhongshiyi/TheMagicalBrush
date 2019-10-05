package com.example.themagicalbrush.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * 项目名：    TheMagicalBrush
 * 包名：      com.example.themagicalbrush.utils
 * 文件名：    UtilTools
 * 作者：      钟士宜
 * 创建时间    2019/10/3 21:42
 * 描述：      工具类统一类
 */
public class UtilTools {
    //设置字体
    public static void setFont(Context context, TextView textView){
        Typeface fontType = Typeface.createFromAsset(context.getAssets(),"fly.ttf");
        textView.setTypeface(fontType);
    }
}
