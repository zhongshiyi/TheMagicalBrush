package com.example.themagicalbrush.draw;

import android.graphics.Paint;
import android.graphics.Path;

/**
 * 项目名：    TheMagicalBrush
 * 包名：      com.example.themagicalbrush.draw
 * 文件名：    DrawPathInfo
 * 作者：      钟士宜
 * 创建时间    2019/10/6 18:57
 * 描述：      TODO
 */
public class DrawPathInfo {
    private Paint paint;

    private Path path;

    public DrawPathInfo(Paint paint, Path path) {
        this.paint = paint;
        this.path = path;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
