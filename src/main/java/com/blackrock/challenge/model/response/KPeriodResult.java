package com.blackrock.challenge.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KPeriodResult {

    private String start;
    private String end;
    private double amount;
    private double profit;
    private double taxBenefit;
}