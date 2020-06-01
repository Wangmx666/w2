package com.wmx.service.book.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wmx.mapper.book.BookMapper;
import com.wmx.model.book.Book;
import com.wmx.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;


    //查询书籍
    @Override
    public Map<String, Object> selectBookList(Book book) {

        //声明一个map  用于放 数据列表和总条数
        Map<String, Object> map = new HashMap<>();
        //开启分页
        Page<Object> page = PageHelper.startPage(book.getPage(), book.getLimit());
        //查询数据
        List<Book> list = bookMapper.selectBookList(book);
        //获取总条数
        long total = page.getTotal();
        //赋值""内必须是data（列表） 和 count（总条数）否则传过去页面不认识
        map.put("code",0);
        map.put("msg", "");
        map.put("data", list);
        map.put("count", total);
        return map;
    }

    //新增书籍
    @Override
    public void insertBook(Book book) {
        bookMapper.insertBook(book);
    }

    //批量删除
    @Override
    public void deletesBook(Book book) {
        bookMapper.deletesBook(book);
    }

    //查询单条
    @Override
    public Book selectDantiao(Book book) {
       Book dantiao = bookMapper.selectDantiao(book);
        return dantiao;
    }

    //修改数据
    @Override
    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }
}
