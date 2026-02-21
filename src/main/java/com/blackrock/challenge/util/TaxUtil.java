package com.blackrock.challenge.util;

public class TaxUtil {

	public static double calculateTax(double income) {

        if (income <= 700000) return 0;

		else if (income <= 1000000)
			return (income - 700000) * 0.10;

		else if (income <= 1200000)
			return 30000 + (income - 1000000) * 0.15;

		else if (income <= 1500000)
			return 60000 + (income - 1200000) * 0.20;

		else
			return 120000 + (income - 1500000) * 0.30;
    }
}