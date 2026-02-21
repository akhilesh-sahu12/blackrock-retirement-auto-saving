package com.blackrock.challenge.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnsResponse {

    private double transactionsTotalAmount;
    private double transactionsTotalCeiling;
    private List<KPeriodResult> savingsByDates;
}