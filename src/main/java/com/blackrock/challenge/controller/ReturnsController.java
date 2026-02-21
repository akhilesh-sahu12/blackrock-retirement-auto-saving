package com.blackrock.challenge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackrock.challenge.model.entity.Transaction;
import com.blackrock.challenge.model.request.ReturnsRequest;
import com.blackrock.challenge.model.response.FilterResponse;
import com.blackrock.challenge.model.response.KPeriodResult;
import com.blackrock.challenge.model.response.ReturnsResponse;
import com.blackrock.challenge.service.ReturnCalculationService;
import com.blackrock.challenge.service.TemporalRuleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/blackrock/challenge/v1")
@RequiredArgsConstructor
public class ReturnsController {

	private final TemporalRuleService temporalRuleService;
	private final ReturnCalculationService returnCalculationService;

	@PostMapping("/returns:nps")
	public ReturnsResponse calculateNPS(@RequestBody ReturnsRequest request) {

		// Filter
		FilterResponse filtered = temporalRuleService.applyTemporalRulesForFilter(request.getTransactions(),
				request.getQ(), request.getP());

		List<Transaction> validTransactions = filtered.getValid();

		// Apply Q/P/K
		List<KPeriodResult> kResults = temporalRuleService.applyTemporalRules(validTransactions, request.getQ(),
				request.getP(), request.getK());

		// Calculate returns
		returnCalculationService.calculateNPS(request.getAge(), request.getWage(), request.getInflation(), kResults);

		// Totals
		double totalAmount = validTransactions.stream().mapToDouble(Transaction::getAmount).sum();

		double totalCeiling = validTransactions.stream().mapToDouble(tx -> Math.ceil(tx.getAmount() / 100.0) * 100)
				.sum();

		return ReturnsResponse.builder().totalTransactionAmount(totalAmount).totalCeiling(totalCeiling)
				.savingsByDates(kResults).build();
	}

	@PostMapping("/returns:index")
	public ReturnsResponse calculateIndex(@RequestBody ReturnsRequest request) {

		// Filter
		FilterResponse filtered = temporalRuleService.applyTemporalRulesForFilter(request.getTransactions(),
				request.getQ(), request.getP());

		List<Transaction> validTransactions = filtered.getValid();

		// Apply Q/P/K
		List<KPeriodResult> kResults = temporalRuleService.applyTemporalRules(validTransactions, request.getQ(),
				request.getP(), request.getK());

		// Step 3: Calculate returns
		returnCalculationService.calculateIndex(request.getAge(), request.getInflation(), kResults);

		// Totals
		double totalAmount = validTransactions.stream().mapToDouble(Transaction::getAmount).sum();

		double totalCeiling = validTransactions.stream().mapToDouble(tx -> Math.ceil(tx.getAmount() / 100.0) * 100)
				.sum();

		return ReturnsResponse.builder().totalTransactionAmount(totalAmount).totalCeiling(totalCeiling)
				.savingsByDates(kResults).build();

	}
}