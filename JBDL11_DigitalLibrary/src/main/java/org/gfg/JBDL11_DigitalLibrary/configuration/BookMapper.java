package org.gfg.JBDL11_DigitalLibrary.configuration;


import org.gfg.JBDL11_DigitalLibrary.model.Book;
import org.gfg.JBDL11_DigitalLibrary.model.BookCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BookMapper implements RowMapper<Book> {


    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getInt("book_id"));
        book.setBookTitle(rs.getString("book_title"));
        book.setBookISBN(rs.getString("book_isbn"));
        book.setBookCategory(BookCategory.valueOf(rs.getString("")));
        book.setBookDescription(rs.getString("book_description"));
        return book;
    }
}
