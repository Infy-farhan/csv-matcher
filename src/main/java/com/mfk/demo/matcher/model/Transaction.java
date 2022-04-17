package com.mfk.demo.matcher.model;

import java.time.LocalDate;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvNumber;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
/**
 * Java Object representing a row in the CSV file
 */
public class Transaction {
	@CsvBindByName(column = "GSTIN")
	private String gstin;

	@CsvCustomBindByName(column = "Date", converter = CustomDateConvertor.class)
	private LocalDate date;

	@CsvBindByName(column = "Bill no")
	private String billNo;

	@CsvBindByName(column = "GST rate(%)")
	private Double gstRate;

	@CsvBindByName(column = "Taxable value")
	@CsvNumber("##,###.##")
	private Double taxableValue;

	@CsvBindByName(column = "IGST")
	@CsvNumber("##,###.##")
	private Double igst;

	@CsvBindByName(column = "CGST")
	@CsvNumber("##,###.##")
	private Double cgst;

	@CsvBindByName(column = "SGST")
	@CsvNumber("##,###.##")
	private Double sgst;
	
	@CsvBindByName(column = "Total")
	@CsvNumber("##,###.##")
	private Double total;

}
