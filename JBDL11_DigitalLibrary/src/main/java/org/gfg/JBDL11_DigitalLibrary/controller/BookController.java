package org.gfg.JBDL11_DigitalLibrary.controller;

import org.gfg.JBDL11_DigitalLibrary.Request.BookCreationRequest;
import org.gfg.JBDL11_DigitalLibrary.Response.BookCreationResponse;
import org.gfg.JBDL11_DigitalLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {


    @Autowired
    BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<BookCreationResponse> createBook(@RequestBody BookCreationRequest bookCreationRequest){
        BookCreationResponse bookCreationResponse = bookService.createBook(bookCreationRequest);
        if(bookCreationResponse.getSuccess()){
            return new ResponseEntity<>(bookCreationResponse, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(bookCreationResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
