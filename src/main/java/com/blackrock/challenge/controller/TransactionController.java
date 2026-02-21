package com.blackrock.challenge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackrock.challenge.model.entity.Transaction;
import com.blackrock.challenge.model.request.FilterRequest;
import com.blackrock.challenge.model.request.ParseRequest;
import com.blackrock.challenge.model.request.ValidatorRequest;
import com.blackrock.challenge.model.response.FilterResponse;
import com.blackrock.challenge.model.response.ValidationResponse;
import com.blackrock.challenge.service.TemporalRuleService;
import com.blackrock.challenge.service.TransactionService;
import com.blackrock.challenge.service.ValidationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/blackrock/challenge/v1")
@RequiredArgsConstructor
public class TransactionController {

	private final TransactionService transactionService;
	private final ValidationService validationService;
	private final TemporalRuleService temporalRuleService;

	// 1️⃣ Parse Transactions
	@PostMapping("/transactions:parse")
	public List<Transaction> parseTransactions(@RequestBody List<ParseRequest> request) {

		return transactionService.parseTransactions(request);
	}

	// 2️⃣ Validate Transactions
	@PostMapping("/transactions:validator")
	public ValidationResponse validateTransactions(@RequestBody ValidatorRequest request) {

		return validationService.validate(request.getWage(), request.getTransactions());
	}

	// 3️⃣ Apply Temporal Rules
	@PostMapping("/transactions:filter")
	public FilterResponse filterTransactions(@RequestBody FilterRequest request) {

		return temporalRuleService.applyTemporalRulesForFilter(request.getTransactions(), request.getQ(),
				request.getP());
	}
}