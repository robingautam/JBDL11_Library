package org.gfg.JBDL11_DigitalLibrary.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreationRequest {

    private Long userId;
    private int bookId;
    private String transactionType; // ISSUE, RETURN, RENEW
    private String remarks;
    private double fineAmount;


}
