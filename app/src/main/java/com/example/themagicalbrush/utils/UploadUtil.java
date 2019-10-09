package com.example.themagicalbrush.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 项目名：    TheMagicalBrush
 * 包名：      com.example.themagicalbrush.utils
 * 文件名：    UploadUtil
 * 作者：      钟士宜
 * 创建时间    2019/10/6 18:58
 * 描述：      TODO
 */
public class UploadUtil {
    private static final String url = "http://deepblue.datsec.cn:8003/upload/";
    public static int flag = 0;
    private static final OkHttpClient client = new OkHttpClient();

    public static int uploadFile(File file) throws IOException {
        RequestBody requestbody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), requestbody)
                .build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = client.newCall(request).execute();
        //储存路径
        String path = Environment.getExternalStorageDirectory().getPath();
        //存储路径
        File appDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        //回传的文件
        File pic = new File(appDir, "new_pic.jpg");
        byte[] buffer = response.body().bytes();
        OutputStream outputStream = new FileOutputStream(pic);
        outputStream.write(buffer);
        outputStream.close();
        if (!response.isSuccessful()) {
            flag = 1;
            return 0;
        } else {
            flag = 1;
            return 1;
        }
    }

}
