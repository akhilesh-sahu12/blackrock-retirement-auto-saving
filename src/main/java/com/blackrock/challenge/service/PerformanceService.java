package com.blackrock.challenge.service;

import com.blackrock.challenge.model.response.PerformanceResponse;

public interface PerformanceService {

	PerformanceResponse measure(Runnable task);
}