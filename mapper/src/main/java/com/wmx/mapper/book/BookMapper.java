package com.wmx.mapper.book;

import com.wmx.model.book.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {

    //查询书籍
    @Select("select t_id bookID," +
            " t_name bookName," +
            " t_author bookAuthor," +
            " t_type bookType," +
            " t_price bookPrice," +
            " t_desc bookDesc," +
            " date_format(t_date,'%Y-%m-%d') bookDate" +
            " from t_books")
    List<Book> selectBookList(Book book);

    //新增书籍
    @Insert("insert into t_books" +
            " (t_name,t_author,t_type,t_price,t_desc,t_date)" +
            " values" +
            " (#{bookName},#{bookAuthor},#{bookType},#{bookPrice},#{bookDesc}," +
            " str_to_date(#{bookDate}, '%Y-%m-%d'))")
    void insertBook(Book book);

    //批量删除
    @Delete("<script>" +
            "delete from t_books " +
            " where t_id in" +
            " <foreach collection='bookIDS.split(\",\")' item='item' separator=',' open=' (' close=')'>" +
            " #{item}" +
            " </foreach>" +
            " </script>")
    void deletesBook(Book book);

    //查询单条
    @Select("select t_id bookID," +
            " t_name bookName," +
            " t_author bookAuthor," +
            " t_type bookType," +
            " t_price bookPrice," +
            " t_desc bookDesc," +
            " date_format(t_date,'%Y-%m-%d') bookDate" +
            " from t_books" +
            " where t_id = #{bookID}")
    Book selectDantiao(Book book);

    //修改数据
    @Update(" update t_books" +
            " set t_name = #{bookName}," +
            " t_author = #{bookAuthor}," +
            " t_type = #{bookType}," +
            " t_price = #{bookPrice}," +
            " t_desc = #{bookDesc}," +
            " t_date = str_to_date(#{bookDate}, '%Y-%m-%d')" +
            " where" +
            " t_id = #{bookID}")
    void updateBook(Book book);
}
