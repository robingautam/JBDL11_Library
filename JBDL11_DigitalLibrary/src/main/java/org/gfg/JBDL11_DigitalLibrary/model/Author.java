package org.gfg.JBDL11_DigitalLibrary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private int authorId;
    private String authorName;
    private String authorEmail;
    private String authorPhone;
    private String authorBio;
    private List<Book> books;

}
