package org.gfg.JBDL11_DigitalLibrary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private int bookId;
    private String bookTitle;
    private Author author;
    private String bookISBN;
    private BookCategory bookCategory;
    private double bookPrice;
    private int bookQuantity;
    private String bookPublisher;
    private String bookDescription;
    private int publicationYear;
    private long createdAt;
}
