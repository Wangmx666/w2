package com.wmx.service.book;

import com.wmx.model.book.Book;

import java.util.Map;

public interface BookService {


    //查询书籍
    Map<String,Object> selectBookList(Book book);

    //新增书籍
    void insertBook(Book book);

    //批量删除
    void deletesBook(Book book);

    //查询单条
    Book selectDantiao(Book book);

    //修改数据
    void updateBook(Book book);
}
