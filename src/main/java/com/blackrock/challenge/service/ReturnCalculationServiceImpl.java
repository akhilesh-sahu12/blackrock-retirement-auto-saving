package com.blackrock.challenge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blackrock.challenge.model.response.KPeriodResult;
import com.blackrock.challenge.util.FinancialUtil;
import com.blackrock.challenge.util.TaxUtil;

@Service
public class ReturnCalculationServiceImpl implements ReturnCalculationService {

	private static final double NPS_RATE = 0.0711;
	private static final double INDEX_RATE = 0.1449;

	@Override
	public void calculateNPS(int age, double wage, double inflation, List<KPeriodResult> kResults) {

		int years = age < 60 ? 60 - age : 5;
		double annualIncome = wage * 12;

		for (KPeriodResult k : kResults) {

			double futureValue = FinancialUtil.compound(k.getAmount(), NPS_RATE, years);

			double adjusted = FinancialUtil.adjustForInflation(futureValue, inflation, years);

			double deduction = Math.min(k.getAmount(), Math.min(annualIncome * 0.10, 200000));

			double taxBenefit = TaxUtil.calculateTax(annualIncome) - TaxUtil.calculateTax(annualIncome - deduction);

			k.setProfit(FinancialUtil.round(adjusted - k.getAmount()));
			k.setTaxBenefit(FinancialUtil.round(taxBenefit));
		}
	}

	@Override
	public void calculateIndex(int age, double inflation, List<KPeriodResult> kResults) {

		int years = age < 60 ? 60 - age : 5;

		for (KPeriodResult k : kResults) {

			double futureValue = FinancialUtil.compound(k.getAmount(), INDEX_RATE, years);

			double adjusted = FinancialUtil.adjustForInflation(futureValue, inflation, years);

			k.setProfit(FinancialUtil.round(adjusted - k.getAmount()));
			k.setTaxBenefit(0);
		}
	}
}