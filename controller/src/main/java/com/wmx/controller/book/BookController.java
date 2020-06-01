package com.wmx.controller.book;

import com.wmx.model.book.Book;
import com.wmx.service.book.BookService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    //跳转查询书籍页面
    @RequestMapping("book/toList")
    String toBookList(Book book) {
        return "book/list";
    }

    //查询书籍
    @RequestMapping("book/select")
    @ResponseBody
    Map<String,Object> selectBookList (Book book) {
        Map<String,Object> map = bookService.selectBookList(book);
        return map;
    }

    //跳转新增书籍页面
    @RequiresPermissions(value={"/book/toAdd"})
    @RequestMapping("book/toAdd")
    String toAdd() {
        return"book/add";
    }


    //新增数据
    @RequiresPermissions(value={"/book/insert"})
    @RequestMapping("book/insert")
    @ResponseBody
    String insertBook(Book book) {
        bookService.insertBook(book);
        return "{}";
    }

    //批量删除数据
    @RequiresPermissions(value={"/book/deletes"})
    @RequestMapping("book/deletes")
    @ResponseBody
    String deletesBook(Book book) {
        bookService.deletesBook(book);
        return "{}";
    }

    //查询单条
    @RequestMapping("book/dantiao")
    String selectDantiao(ModelMap mm, Book book) {
        Book dantiao = bookService.selectDantiao(book);
        mm.addAttribute("dantiao", dantiao);
        return"book/update";
    }

    //修改数据
    @RequestMapping("book/update")
    @ResponseBody
    String updateBook(Book book) {
        bookService.updateBook(book);
        return"{}";
    }

}
