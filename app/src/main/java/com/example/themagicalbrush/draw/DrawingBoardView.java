package com.example.themagicalbrush.draw;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Environment;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.themagicalbrush.utils.UploadUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：    TheMagicalBrush
 * 包名：      com.example.themagicalbrush.view
 * 文件名：    DrawingBoardView
 * 作者：      钟士宜
 * 创建时间    2019/10/6 18:53
 * 描述：      画板View
 */
public class DrawingBoardView extends View {
    private File file;
    public static int flag = 0;
    public static String fileName;

    // 画图的模式（默认是画笔）
    private DrawMode mDrawMode = DrawMode.PaintMode;
    // 画笔
    private Paint mPaint;
    // 画笔颜色
    private int mPaintColor = Color.BLACK;
    // 画笔宽度
    private int mPaintSize = diptopx(7);
    // 橡皮擦的宽度
    private int mEraserSize = diptopx(36);
    // 缓冲的位图
    private Bitmap mBufferBitmap;
    // 缓冲的画布
    private Canvas mBufferCanvas;
    // 当前控件的宽
    private int mWidth;
    // 当前控件的高
    private int mHight;
    // 画布的颜色
    final private int mCanvasColor = Color.WHITE;
    // 上次的位置
    private float mLastX;
    private float mLastY;
    // 路径
    private Path mPath;
    // 设置图形混合模式为清除
    private PorterDuffXfermode mEraserMode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    // 保存的路径
    private List<DrawPathInfo> savePaths;
    // 当前的路径
    private List<DrawPathInfo> currPaths;
    // 最多保存20条路径
    private int MAX_PATH = 20;




    public DrawingBoardView(Context context) {
        this(context, null);
    }

    public DrawingBoardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawingBoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPath();
        initPaint();
    }

    /**
     * 初始化路径
     */
    private void initPath() {
        mPath = new Path();
        savePaths = new ArrayList<DrawPathInfo>();
        currPaths = new ArrayList<DrawPathInfo>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHight);
        initCanvas();
    }

    /**
     * 初始化缓冲画布
     */
    private void initCanvas() {
        // 创建一个BITMAP
        mBufferBitmap = Bitmap.createBitmap(mWidth, mHight, Bitmap.Config.ARGB_8888);
        // 创建一个画布，所有mBufferCanvas画的东西都被保存在了mBufferBitmap中
        mBufferCanvas = new Canvas(mBufferBitmap);
        // 设置画布颜色
        mBufferCanvas.drawColor(mCanvasColor);
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        // 设置画笔抗锯齿和抖动
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        // 画笔只描边
        mPaint.setStyle(Paint.Style.STROKE);
        // 设置画笔颜色
        mPaint.setColor(mPaintColor);
        // 设置画笔宽度
        mPaint.setStrokeWidth(mPaintSize);
        // 设置圆形线帽
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        // 设置线段连接处圆角
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBufferBitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
                // 画出路径
                mPath.quadTo(mLastX, mLastY, (mLastX + x) / 2, (mLastY + y) / 2);
                mBufferCanvas.drawPath(mPath, mPaint);
                invalidate();
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                // 保存路径
                saveDrawPaths();
                mPath.reset();
                break;
        }
        return true;
    }

    /**
     * 保存画的路径
     */
    private void saveDrawPaths() {
        if (savePaths.size() == MAX_PATH) {
            savePaths.remove(0);
        }
        savePaths.clear();
        savePaths.addAll(currPaths);
        Path cachePath = new Path(mPath);
        Paint cachePaint = new Paint(mPaint);
        savePaths.add(new DrawPathInfo(cachePaint, cachePath));
        currPaths.add(new DrawPathInfo(cachePaint, cachePath));
    }

    public int diptopx(float dipValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 擦除画布
     */
    public void clean() {
        savePaths.clear();
        currPaths.clear();
        //擦成白色的
        mBufferBitmap.eraseColor(Color.WHITE);
        invalidate();
    }

    /**
     * 设置画笔模式
     *
     * @param mode
     */
    public void setMode(DrawMode mode) {
        if (mode != mDrawMode) {
            if (mode == DrawMode.EraserMode) {
                mPaint.setStrokeWidth(mEraserSize);
                mPaint.setXfermode(mEraserMode);
                mPaint.setColor(Color.WHITE);
            } else {
                mPaint.setXfermode(null);
                mPaint.setStrokeWidth(mPaintSize);
                mPaint.setColor(Color.BLACK);
            }
            mDrawMode = mode;
        }
    }

    /**
     * 获取当前模式
     *
     * @return
     */
    public DrawMode getMode() {
        return mDrawMode;
    }

    /**
     * 上一步  撤销
     */
    public void lastStep() {
        if (currPaths.size() > 0) {
            currPaths.remove(currPaths.size() - 1);
            redrawBitmap();
        }
    }

    /**
     * 下一步   反撤销
     */
    public void nextStep() {
        if (currPaths != savePaths) {
            if (savePaths.size() > currPaths.size()) {
                currPaths.add(savePaths.get(currPaths.size()));
                redrawBitmap();
            }
        }
    }

    private static final String TAG = "DrawingBoardView";

    /**
     * 重绘位图
     */
    private void redrawBitmap() {
        mBufferBitmap.eraseColor(Color.WHITE);
        for (int i = 0; i < currPaths.size(); i++) {
            DrawPathInfo path = currPaths.get(i);
            mBufferCanvas.drawPath(path.getPath(), path.getPaint());
        }
        invalidate();
    }

    /**
     * 保存图片
     */
    @SuppressLint("WrongThread")
    public void save() {
        mBufferCanvas.save();
        mBufferCanvas.restore();
        //存储路径
        File appDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (appDir == null) {
            return;
        }
        fileName = System.currentTimeMillis() + ".jpg";
        file = new File(appDir, fileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            mBufferBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Looper.prepare();
                    int result = 0;
                    try {
                        result = UploadUtil.uploadFile(file);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (result == 1) {
//                        Message message=new Message();
//                        message.what=1;
//                        Handler handler=new Handler();
//                        handler.
//                        messageHandler.sendMessage(message);
                        flag = 1;
                    } else {
                        try {
                            UploadUtil.uploadFile(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Looper.loop();
                }
            }).start();
            fileOutputStream.close();
            Toast.makeText(getContext(), "保存成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

