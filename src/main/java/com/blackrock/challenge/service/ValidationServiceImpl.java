package com.blackrock.challenge.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.blackrock.challenge.model.entity.InvalidTransaction;
import com.blackrock.challenge.model.entity.Transaction;
import com.blackrock.challenge.model.response.ValidationResponse;

@Service
public class ValidationServiceImpl implements ValidationService {

	@Override
	public ValidationResponse validate(double wage, List<Transaction> transactions) {

        Set<String> seenDates = new HashSet<>();
        List<Transaction> valid = new ArrayList<>();
        List<InvalidTransaction> invalid = new ArrayList<>();

        for (Transaction tx : transactions) {

            if (tx.getAmount() < 0) {
				invalid.add(
						InvalidTransaction.builder().date(tx.getDate()).amount(tx.getAmount()).ceiling(tx.getCeiling())
								.remanent(tx.getRemanent()).message("Negative amounts are not allowed").build());
                continue;
            }

            if (!seenDates.add(tx.getDate())) {
				invalid.add(InvalidTransaction.builder().date(tx.getDate()).amount(tx.getAmount())
						.ceiling(tx.getCeiling()).remanent(tx.getRemanent()).message("Duplicate transaction").build());
                continue;
            }

            valid.add(tx);
        }

        return ValidationResponse.builder()
                .valid(valid)
                .invalid(invalid)
                .build();
    }
}