package org.gfg.JBDL11_DigitalLibrary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private Long transactionId;
    private Long userId;
    private int bookId;
    private String transactionType; // ISSUE, RETURN, RENEW
    private long issueDate;
    private long dueDate;
    private long returnDate;
    private String status; // ACTIVE, RETURNED, OVERDUE
    private String remarks;
    private long createdAt;
    private long updatedAt;
    private double fineAmount;
}
