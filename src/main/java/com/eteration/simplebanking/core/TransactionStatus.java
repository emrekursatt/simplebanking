package com.eteration.simplebanking.core;


import lombok.Builder;

import java.util.UUID;

@Builder
public class TransactionStatus {
    private String status;
    private UUID approvalCode;

    public TransactionStatus(String status , UUID approvalCode) {
        this.status = status;
        this.approvalCode = approvalCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(UUID approvalCode) {
        this.approvalCode = approvalCode;
    }
}
