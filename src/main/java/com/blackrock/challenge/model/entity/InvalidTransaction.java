package com.blackrock.challenge.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvalidTransaction {

    private String date;
    private double amount;
    private double ceiling;
    private double remanent;
    private String message;
}