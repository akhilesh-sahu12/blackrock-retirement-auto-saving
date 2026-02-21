package com.blackrock.challenge.service;

import java.util.List;

import com.blackrock.challenge.model.entity.Transaction;
import com.blackrock.challenge.model.request.ParseRequest;

public interface TransactionService {

	List<Transaction> parseTransactions(List<ParseRequest> expenses);
}