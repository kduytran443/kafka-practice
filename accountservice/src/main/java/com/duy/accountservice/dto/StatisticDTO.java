package com.duy.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StatisticDTO {
    private String message;
    private LocalDateTime createdTime;
}
