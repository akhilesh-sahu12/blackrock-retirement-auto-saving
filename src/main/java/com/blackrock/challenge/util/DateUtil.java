package com.blackrock.challenge.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {


	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime parse(String date) {

		try {
			return LocalDateTime.parse(date, FORMATTER);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid date format. Required: yyyy-MM-dd HH:mm:ss");
		}
    }

	public static boolean inRange(LocalDateTime d, LocalDateTime start, LocalDateTime end) {
		return !d.isBefore(start) && !d.isAfter(end);
    }

	public static DateTimeFormatter getFormatter() {
		return FORMATTER;
	}

}