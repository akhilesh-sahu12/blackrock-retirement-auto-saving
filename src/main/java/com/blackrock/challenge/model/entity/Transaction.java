package com.blackrock.challenge.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    private String date;        
    private double amount;      
    private double ceiling;     
    private double remanent;    
}