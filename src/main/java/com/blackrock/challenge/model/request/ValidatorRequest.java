package com.blackrock.challenge.model.request;


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
public class ValidatorRequest {

    private double wage;
    private List<Transaction> transactions;
}