package com.example.themagicalbrush.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.themagicalbrush.R;
import com.example.themagicalbrush.ShowPic;
import com.example.themagicalbrush.draw.DrawMode;
import com.example.themagicalbrush.draw.DrawingBoardView;
import com.example.themagicalbrush.utils.UploadUtil;

public class DrawingBoard extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivPaint;
    private ImageView ivClean;
    private ImageView ivLast;
    private ImageView ivNext;
    private ProgressBar mProgressBar;
    private ImageView ivSave;
    private DrawingBoardView mDrawingBoardView;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing_board);
        initView();
        initEvent();
    }


    private void initView() {
        mDrawingBoardView = (DrawingBoardView) findViewById(R.id.dbv);
        ivPaint = (ImageView) findViewById(R.id.ivPaint);
        ivClean = (ImageView) findViewById(R.id.ivClean);
        ivLast = (ImageView) findViewById(R.id.ivLast);
        ivNext = (ImageView) findViewById(R.id.ivNext);
        ivSave = (ImageView) findViewById(R.id.ivSave);
        mProgressBar = (ProgressBar) findViewById(R.id.wating);
    }

    private void initEvent() {
        mProgressBar.setVisibility(View.INVISIBLE);
        ivPaint.getDrawable().setLevel(1);
        ivPaint.getBackground().setLevel(1);
        ivPaint.setOnClickListener(this);
        ivClean.setOnClickListener(this);
        ivLast.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        ivSave.setOnClickListener(this);

        //检测是否有写的权限
        int permission = ActivityCompat.checkSelfPermission(this,
                "android.permission.WRITE_EXTERNAL_STORAGE");
        if (permission != PackageManager.PERMISSION_GRANTED) {
            try {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivPaint:
                if (mDrawingBoardView.getMode() != DrawMode.PaintMode) {
                    mDrawingBoardView.setMode(DrawMode.PaintMode);
                    ivPaint.getDrawable().setLevel(1);
                    ivPaint.getBackground().setLevel(1);
                }
                break;
            case R.id.ivClean:
                alertDialogClean();
                break;
            case R.id.ivLast:
                mDrawingBoardView.lastStep();
                break;
            case R.id.ivNext:
                mDrawingBoardView.nextStep();
                break;
            case R.id.ivSave:
                mProgressBar.setVisibility(View.VISIBLE);
                mDrawingBoardView.save();
                int j = 0;
                try {
                    for (int i = 0; i < 60; i++) {
                        if (UploadUtil.flag != 1) {
                            Thread.sleep(100);
                            j++;
                        } else {
                            if (j == 59) {
                                Toast.makeText(DrawingBoard.this, "传输出错请重试！", Toast.LENGTH_SHORT).show();
                                mProgressBar.setVisibility(View.INVISIBLE);
                            }
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mProgressBar.setVisibility(View.INVISIBLE);
                UploadUtil.flag = 0;
                Intent intent = new Intent(DrawingBoard.this, ShowPic.class);
                startActivity(intent);
                break;
        }
    }

    public void alertDialogClean() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定要清空画板吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mDrawingBoardView.clean();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        final AlertDialog dialog = builder.show();
        dialog.show();
    }
}
