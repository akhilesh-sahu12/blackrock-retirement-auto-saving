package com.blackrock.challenge.util;

public class FinancialUtil {

    public static double calculateCeiling(double amount) {
        return Math.ceil(amount / 100.0) * 100;
    }

    public static double calculateRemanent(double amount) {
        return calculateCeiling(amount) - amount;
    }

	public static double compound(double principal, double rate, int years) {

		return principal * Math.pow(1 + rate, years);
	}

	public static double adjustForInflation(double amount, double inflation, int years) {

		return amount / Math.pow(1 + inflation / 100.0, years);
	}

	public static double round(double value) {
		return java.math.BigDecimal.valueOf(value).setScale(2, java.math.RoundingMode.HALF_UP).doubleValue();
	}



}
