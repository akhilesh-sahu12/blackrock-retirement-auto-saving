package com.blackrock.challenge.service;

import java.util.List;

import com.blackrock.challenge.model.entity.Transaction;
import com.blackrock.challenge.model.response.ValidationResponse;

public interface ValidationService {

	ValidationResponse validate(double wage, List<Transaction> transactions);
}