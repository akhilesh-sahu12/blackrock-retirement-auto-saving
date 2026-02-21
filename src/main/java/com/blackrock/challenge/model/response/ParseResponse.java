package com.blackrock.challenge.model.response;


import java.util.List;

import com.blackrock.challenge.model.entity.Transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParseResponse {

    private List<Transaction> transactions;
    private double totalAmount;
    private double totalCeiling;
    private double totalRemanent;
}