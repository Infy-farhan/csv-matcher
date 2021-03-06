package com.mfk.demo.matcher.model;

import java.time.LocalDate;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

/**
 * A custom converter used to convert a String value to a LocalDate object.
 * Used to populate the Date column of the csv into the date field of the Transaction object
 */
public class CustomDateConvertor extends AbstractBeanField<LocalDate, Object>{

	@Override
	protected LocalDate convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		String[] partials = null;
		if(value.contains("/")) {
			partials = value.split("/");
		}else if(value.contains("-")) {
			partials = value.split("-");
		}else {
			throw new CsvDataTypeMismatchException("Date format not supported");
		}
		int day = Integer.parseInt(partials[0]);
		int month = Integer.parseInt(partials[1]);
		if(partials[2].length()==2) partials[2] = "20" + partials[2];
		int year = Integer.parseInt(partials[2]);
		return LocalDate.of(year, month, day);
	}

}
