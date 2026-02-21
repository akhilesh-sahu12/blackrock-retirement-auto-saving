package com.blackrock.challenge.model.response;

import java.util.List;

import com.blackrock.challenge.model.entity.InvalidTransaction;
import com.blackrock.challenge.model.entity.Transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilterResponse {

    private List<Transaction> valid;
    private List<InvalidTransaction> invalid;
}