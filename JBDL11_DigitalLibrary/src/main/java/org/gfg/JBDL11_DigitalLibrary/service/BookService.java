package org.gfg.JBDL11_DigitalLibrary.service;

import org.gfg.JBDL11_DigitalLibrary.Repository.BookRepository;
import org.gfg.JBDL11_DigitalLibrary.Request.BookCreationRequest;
import org.gfg.JBDL11_DigitalLibrary.Response.BookCreationResponse;
import org.gfg.JBDL11_DigitalLibrary.model.Author;
import org.gfg.JBDL11_DigitalLibrary.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;


    public BookCreationResponse createBook(BookCreationRequest bookCreationRequest){
        // check if book already exists or not if exists then return failure response

        Book book = bookRepository.findBookByIsbn(bookCreationRequest.getBookISBN());
        if(book != null) {
            return new BookCreationResponse(false, "Book with this ISBN already exists!", null);
        }

        // create the author
        Author author = new Author();
        author.setAuthorName(bookCreationRequest.getAuthorName());
        author.setAuthorEmail(bookCreationRequest.getAuthorEmail());
        author.setAuthorPhone(bookCreationRequest.getAuthorPhone());
        author.setAuthorBio(bookCreationRequest.getAuthorBio());

        // check if author already exists or not if exists then return author id
        Author existingAuthor = bookRepository.findAuthorByEmail(bookCreationRequest.getAuthorEmail());
        if(existingAuthor != null) {
            author.setAuthorId(existingAuthor.getAuthorId());
        } else {
            // save the author to database and get the author id
            try {
               int authorId = bookRepository.saveAuthorAndReturnAuthorId(author);
                author.setAuthorId(authorId);
            } catch (Exception e) {
                System.out.println("Error creating author: " + e.getMessage());
                return new BookCreationResponse(false, "Error creating author: " + e.getMessage(), null);
            }
        }

        // create book
        Book newBook = new Book();
        newBook.setBookTitle(bookCreationRequest.getBookTitle());
        newBook.setBookISBN(bookCreationRequest.getBookISBN());
        newBook.setBookCategory(bookCreationRequest.getBookCategory());
        newBook.setBookPrice(bookCreationRequest.getBookPrice());
        newBook.setBookQuantity(bookCreationRequest.getBookQuantity());
        newBook.setBookPublisher(bookCreationRequest.getBookPublisher());
        newBook.setBookDescription(bookCreationRequest.getBookDescription());
        newBook.setPublicationYear(bookCreationRequest.getPublicationYear());
        newBook.setAuthor(author);

        try {
            // save the book and author to database
            bookRepository.saveBook(newBook);
        }
        catch (Exception e){
            new BookCreationResponse(false, "Error creating book: " + e.getMessage(), null);
        }

        return new BookCreationResponse(true, "Book created successfully!", newBook);


    }
}
