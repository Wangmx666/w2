package com.wmx.model.book;

import com.wmx.common.PageUtil;

public class Book extends PageUtil{

    private int bookID;

    private String bookName;

    private String bookAuthor;

    private int bookType;

    private float bookPrice;

    private String bookDesc;

    private String bookDate;

    /** 业务字段*/
    private String bookIDS;

    public String getBookIDS() {
        return bookIDS;
    }

    public void setBookIDS(String bookIDS) {
        this.bookIDS = bookIDS;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookType() {
        return bookType;
    }

    public void setBookType(int bookType) {
        this.bookType = bookType;
    }

    public float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }
}
