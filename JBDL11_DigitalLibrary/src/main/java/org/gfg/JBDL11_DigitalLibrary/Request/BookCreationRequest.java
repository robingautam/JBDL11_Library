package org.gfg.JBDL11_DigitalLibrary.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gfg.JBDL11_DigitalLibrary.model.BookCategory;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCreationRequest {
    private String bookTitle;
    private String authorName;
    private String authorEmail;
    private String authorPhone;
    private String authorBio;
    private String bookISBN;
    private BookCategory bookCategory;
    private double bookPrice;
    private int bookQuantity;
    private String bookPublisher;
    private String bookDescription;
    private int publicationYear;
}
