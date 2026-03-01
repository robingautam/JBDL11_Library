package org.gfg.JBDL11_DigitalLibrary.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gfg.JBDL11_DigitalLibrary.model.Transaction;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionCreationResponse extends Response {
    Transaction transaction;
}
