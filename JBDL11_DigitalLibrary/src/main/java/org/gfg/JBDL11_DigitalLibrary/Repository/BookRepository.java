package org.gfg.JBDL11_DigitalLibrary.Repository;

import org.gfg.JBDL11_DigitalLibrary.configuration.BookMapper;
import org.gfg.JBDL11_DigitalLibrary.model.Author;
import org.gfg.JBDL11_DigitalLibrary.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class BookRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String BOOK_ISBN_QUERY = "SELECT * FROM BOOK WHERE BOOK_ISBN = ";

    public Book findBookByIsbn(String bookIsbn) {
        try {
            return jdbcTemplate.queryForObject(BOOK_ISBN_QUERY + bookIsbn, new BookMapper());
        }
        catch (Exception e){
            return null;
        }
    }

    public Author findAuthorByEmail(String authorEmail) {
        try {
            String AUTHOR_EMAIL_QUERY = "SELECT * FROM authors WHERE author_email = '" + authorEmail + "'";
            return jdbcTemplate.queryForObject(AUTHOR_EMAIL_QUERY, (rs, rowNum) -> {
                Author author = new Author();
                author.setAuthorId(rs.getInt("author_id"));
                author.setAuthorName(rs.getString("author_name"));
                author.setAuthorEmail(rs.getString("author_email"));
                author.setAuthorPhone(rs.getString("author_phone"));
                return author;
            });
        }
        catch (Exception e){
            return null;
        }
    }


    public int saveAuthorAndReturnAuthorId(Author author) {
        String INSERT_AUTHOR_QUERY = "INSERT INTO authors (author_name, author_email, author_phone, author_bio) VALUES (?, ?, ?,?)";
        jdbcTemplate.update(INSERT_AUTHOR_QUERY, author.getAuthorName(), author.getAuthorEmail(), author.getAuthorPhone(),author.getAuthorBio());
        String LAST_INSERT_ID_QUERY = "SELECT LAST_INSERT_ID()";
        return jdbcTemplate.queryForObject(LAST_INSERT_ID_QUERY, Integer.class);
    }


    public void saveBook(Book book) {
        String INSERT_BOOK_QUERY = "INSERT INTO books (book_title, book_isbn, book_category, book_price, book_quantity, book_publisher, book_description, publication_year, author_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(INSERT_BOOK_QUERY,
                book.getBookTitle(),
                book.getBookISBN(),
                book.getBookCategory().name(),
                book.getBookPrice(),
                book.getBookQuantity(),
                book.getBookPublisher(),
                book.getBookDescription(),
                book.getPublicationYear(),
                book.getAuthor().getAuthorId()
        );
        System.out.println("Book saved successfully!");
    }




}
