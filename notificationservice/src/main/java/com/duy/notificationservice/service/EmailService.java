package com.duy.notificationservice.service;

import com.duy.notificationservice.dto.MessageDTO;

public interface EmailService {
    void sendMail(MessageDTO messageDTO);
}
