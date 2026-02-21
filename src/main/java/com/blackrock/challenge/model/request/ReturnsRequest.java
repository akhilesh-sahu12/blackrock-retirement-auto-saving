package com.blackrock.challenge.model.request;

import java.util.List;

import com.blackrock.challenge.model.entity.PeriodK;
import com.blackrock.challenge.model.entity.PeriodP;
import com.blackrock.challenge.model.entity.PeriodQ;
import com.blackrock.challenge.model.entity.Transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnsRequest {

	private Integer age;
	private Double wage;
	private Double inflation;

    private List<PeriodQ> q;
    private List<PeriodP> p;
    private List<PeriodK> k;

    private List<Transaction> transactions;
}