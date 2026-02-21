package com.blackrock.challenge.service;

import java.util.List;

import com.blackrock.challenge.model.response.KPeriodResult;

public interface ReturnCalculationService {

	void calculateNPS(int age, double wage, double inflation, List<KPeriodResult> kResults);

	void calculateIndex(int age, double inflation, List<KPeriodResult> kResults);
}