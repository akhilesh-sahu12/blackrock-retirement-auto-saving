package com.blackrock.challenge.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.blackrock.challenge.model.entity.InvalidTransaction;
import com.blackrock.challenge.model.entity.PeriodK;
import com.blackrock.challenge.model.entity.PeriodP;
import com.blackrock.challenge.model.entity.PeriodQ;
import com.blackrock.challenge.model.entity.Transaction;
import com.blackrock.challenge.model.response.FilterResponse;
import com.blackrock.challenge.model.response.KPeriodResult;
import com.blackrock.challenge.util.DateUtil;
import com.blackrock.challenge.util.FinancialUtil;

@Service
public class TemporalRuleServiceImpl implements TemporalRuleService {

	@Override
	public List<KPeriodResult> applyTemporalRules(List<Transaction> transactions, List<PeriodQ> qList,
			List<PeriodP> pList, List<PeriodK> kList) {

		if (transactions == null)
			transactions = new ArrayList<>();
		if (qList == null)
			qList = new ArrayList<>();
		if (pList == null)
			pList = new ArrayList<>();
		if (kList == null)
			kList = new ArrayList<>();

		List<Transaction> processed = new ArrayList<>();

		// STEP 1,2,3 (Ceiling → Q → P)
		for (Transaction tx : transactions) {

			double amount = tx.getAmount();
			double ceiling = Math.ceil(amount / 100.0) * 100;
			double remanent = ceiling - amount;

			LocalDateTime txDate = DateUtil.parse(tx.getDate());

			// Q override (latest start wins)
			Optional<PeriodQ> matchedQ = qList.stream()
					.filter(q -> DateUtil.inRange(txDate, q.getStart(), q.getEnd()))
					.max(Comparator.comparing(PeriodQ::getStart));

			if (matchedQ.isPresent()) {
				remanent = matchedQ.get().getFixed();
			}

			// P addition (add all)
			double extra = pList.stream().filter(p -> DateUtil.inRange(txDate, p.getStart(), p.getEnd()))
					.mapToDouble(PeriodP::getExtra).sum();

			remanent += extra;

			Transaction newTx = new Transaction();
			newTx.setDate(tx.getDate());
			newTx.setAmount(amount);
			newTx.setCeiling(ceiling);
			newTx.setRemanent(remanent);

			processed.add(newTx);
		}

		// STEP 4 (Group by K)
		List<KPeriodResult> results = new ArrayList<>();

		for (PeriodK k : kList) {

			double sum = processed.stream()
					.filter(tx -> DateUtil.inRange(DateUtil.parse(tx.getDate()), k.getStart(), k.getEnd()))
					.mapToDouble(Transaction::getRemanent).sum();

			results.add(KPeriodResult.builder().start(k.getStart().format(DateUtil.getFormatter()))
					.end(k.getEnd().format(DateUtil.getFormatter())).amount(FinancialUtil.round(sum)).profit(0)
					.taxBenefit(0)
					.build());
		}

		return results;
	}


	@Override
	public FilterResponse applyTemporalRulesForFilter(List<Transaction> transactions, List<PeriodQ> qList,
			List<PeriodP> pList) {

		if (transactions == null)
			transactions = new ArrayList<>();
		if (qList == null)
			qList = new ArrayList<>();
		if (pList == null)
			pList = new ArrayList<>();

		List<Transaction> valid = new ArrayList<>();
		List<InvalidTransaction> invalid = new ArrayList<>();

		Map<String, Integer> dateCount = new HashMap<>();

		// Step 1: Count date occurrences
		for (Transaction tx : transactions) {
			dateCount.put(tx.getDate(), dateCount.getOrDefault(tx.getDate(), 0) + 1);
		}

		Set<String> duplicateAdded = new HashSet<>();

		// Step 2: Process
		for (Transaction tx : transactions) {

			String date = tx.getDate();

			// Duplicate check
			if (dateCount.get(date) > 1) {

				// Add only once
				if (!duplicateAdded.contains(date)) {

					InvalidTransaction inv = new InvalidTransaction();
					inv.setDate(date);
					inv.setAmount(tx.getAmount());
					inv.setMessage("Duplicate transaction");

					invalid.add(inv);
					duplicateAdded.add(date);
				}

				continue; // Skip completely
			}

			// Negative check
			if (tx.getAmount() == null || tx.getAmount() < 0) {

				InvalidTransaction inv = new InvalidTransaction();
				inv.setDate(date);
				inv.setAmount(tx.getAmount());
				inv.setMessage("Negative amounts are not allowed");

				invalid.add(inv);
				continue;
			}

			// Apply Q & P logic
			double amount = tx.getAmount();
			double ceiling = Math.ceil(amount / 100.0) * 100;
			double remanent = ceiling - amount;

			LocalDateTime txDate = DateUtil.parse(date);

			Optional<PeriodQ> matchedQ = qList.stream().filter(q -> DateUtil.inRange(txDate, q.getStart(), q.getEnd()))
					.max(Comparator.comparing(PeriodQ::getStart));

			if (matchedQ.isPresent()) {
				remanent = matchedQ.get().getFixed();
			}

			double extra = pList.stream().filter(p -> DateUtil.inRange(txDate, p.getStart(), p.getEnd()))
					.mapToDouble(PeriodP::getExtra).sum();

			remanent += extra;

			Transaction updated = new Transaction();
			updated.setDate(date);
			updated.setAmount(amount);
			updated.setCeiling(ceiling);
			updated.setRemanent(remanent);

			valid.add(updated);
		}

		return FilterResponse.builder().valid(valid).invalid(invalid).build();
	}
}