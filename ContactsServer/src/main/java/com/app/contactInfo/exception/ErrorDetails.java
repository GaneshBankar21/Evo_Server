package com.app.contactInfo.exception;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ErrorDetails {
    private String status;
    private Date timestamp;
    private List<String> message;

    public ErrorDetails(Date timestamp, List message, String status) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public List<String> getMessage() {
         return message;
    }

    public String getDetails() {
         return status;
    }
}