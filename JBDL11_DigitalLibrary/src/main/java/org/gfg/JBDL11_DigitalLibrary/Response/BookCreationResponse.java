package org.gfg.JBDL11_DigitalLibrary.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gfg.JBDL11_DigitalLibrary.model.Book;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCreationResponse extends Response{
    private Book book;
}
