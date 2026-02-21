package com.blackrock.challenge.service;

import org.springframework.stereotype.Service;

import com.blackrock.challenge.model.response.PerformanceResponse;

@Service
public class PerformanceServiceImpl implements PerformanceService {

	@Override
	public PerformanceResponse measure(Runnable task) {

		long start = System.currentTimeMillis();
		task.run();
		long end = System.currentTimeMillis();

		long duration = end - start;

		Runtime runtime = Runtime.getRuntime();
		double memory = (runtime.totalMemory() - runtime.freeMemory()) / (1024.0 * 1024);

		int threads = Thread.activeCount();

		return PerformanceResponse.builder().time(duration + " ms").memory(String.format("%.2f MB", memory))
				.threads(threads).build();
	}
}