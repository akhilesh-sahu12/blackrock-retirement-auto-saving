package com.blackrock.challenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackrock.challenge.model.response.PerformanceResponse;
import com.blackrock.challenge.service.PerformanceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/blackrock/challenge/v1")
@RequiredArgsConstructor
public class PerformanceController {

	private final PerformanceService performanceService;

	@GetMapping("/performance")
	public PerformanceResponse measurePerformance() {

		return performanceService.measure(() -> {
			// Dummy heavy task (can be replaced)
			for (int i = 0; i < 1_000_000; i++) {
				Math.sqrt(i);
			}
		});
	}
}