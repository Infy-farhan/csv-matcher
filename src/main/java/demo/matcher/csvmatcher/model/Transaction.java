package demo.matcher.csvmatcher.model;

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
public class Transaction {
	@CsvBindByName(column = "GSTIN")
	private String gstin;

	@CsvCustomBindByName(column = "Date", converter = CustomDateConvertor.class)
	private LocalDate date;

	@CsvBindByName(column = "Bill no")
	private String billNo;

	@CsvBindByName(column = "GST rate(%)")
	private Float gstRate;

	@CsvBindByName(column = "Taxable value")
	@CsvNumber("##,###.##")
	private Float taxableValue;

	@CsvBindByName(column = "IGST")
	@CsvNumber("##,###.##")
	private Float igst;

	@CsvBindByName(column = "CGST")
	@CsvNumber("##,###.##")
	private Float cgst;

	@CsvBindByName(column = "SGST")
	@CsvNumber("##,###.##")
	private Float sgst;
	
	@CsvBindByName(column = "Total")
	@CsvNumber("##,###.##")
	private Float total;
}
