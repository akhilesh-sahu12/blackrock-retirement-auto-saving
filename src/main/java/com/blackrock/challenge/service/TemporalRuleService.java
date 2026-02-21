package com.blackrock.challenge.service;

import java.util.List;

import com.blackrock.challenge.model.entity.PeriodK;
import com.blackrock.challenge.model.entity.PeriodP;
import com.blackrock.challenge.model.entity.PeriodQ;
import com.blackrock.challenge.model.entity.Transaction;
import com.blackrock.challenge.model.response.FilterResponse;
import com.blackrock.challenge.model.response.KPeriodResult;

public interface TemporalRuleService {

	List<KPeriodResult> applyTemporalRules(List<Transaction> transactions, List<PeriodQ> qList, List<PeriodP> pList,
			List<PeriodK> kList);

	FilterResponse applyTemporalRulesForFilter(List<Transaction> transactions, List<PeriodQ> qList,
			List<PeriodP> pList);
}