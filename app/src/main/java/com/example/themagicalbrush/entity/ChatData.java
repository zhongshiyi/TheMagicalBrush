package com.example.themagicalbrush.entity;

/**
 * 项目名：    TheMagicalBrush
 * 包名：      com.example.themagicalbrush.entity
 * 文件名：    ChatData
 * 作者：      钟士宜
 * 创建时间    2019/9/16 21:20
 * 描述：      社区实体类
 */
public class ChatData {

    //图片来源地址
    private String Imagurl;
    //作者
    private String author;
    //多少人喜欢
    private int number;

    public String getImagurl() {
        return Imagurl;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumber() {
        return number;
    }

    public void setImagurl(String imagurl) {
        Imagurl = imagurl;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
