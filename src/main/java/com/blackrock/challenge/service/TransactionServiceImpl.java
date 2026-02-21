package com.blackrock.challenge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blackrock.challenge.model.entity.Transaction;
import com.blackrock.challenge.model.request.ParseRequest;
import com.blackrock.challenge.util.FinancialUtil;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Override
	public List<Transaction> parseTransactions(List<ParseRequest> expenses) {

		List<Transaction> transactions = expenses.stream()
                .map(exp -> {
					double ceiling = FinancialUtil.calculateCeiling(exp.getAmount());
					double remanent = ceiling - exp.getAmount();

                    return Transaction.builder()
                            .date(exp.getDate())
							.amount(exp.getAmount()).ceiling(ceiling).remanent(remanent)
                            .build();
                }).toList();


		return transactions;
    }
}