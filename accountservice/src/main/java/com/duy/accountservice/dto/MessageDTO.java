package com.duy.accountservice.dto;

import lombok.Data;

@Data
public class MessageDTO {
    private String to;
    private String toName;
    private String subject;
    private String content;

    public MessageDTO(AccountDTO account, String subject, String content) {
        this.to = account.getEmail();
        this.toName = account.getName();
        this.subject = subject;
        this.content = content;
    }

}
