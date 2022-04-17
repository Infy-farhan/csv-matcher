package demo.matcher.csvmatcher.file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import demo.matcher.csvmatcher.matcher.Match;
import demo.matcher.csvmatcher.model.Transaction;

/**
 * Provides utility methods to read and writes CSV files to and from java beans
 * (Transaction objects)
 */
public class CsvFileUtil {

	/**
	 * Reads the contents of a CSV file, converts each row into a Transaction object
	 * and returns the list of Transaction objects
	 * 
	 * @param path path to the CSV file
	 * @return list of Transaction objects representing each row of the file
	 */
	public static List<Transaction> readCsv(String path) throws FileNotFoundException {
		List<Transaction> transactions = new CsvToBeanBuilder<Transaction>(new FileReader(path))
				.withSeparator(',')
				.withType(Transaction.class)
				.build()
				.parse();
		return transactions;
	}

	/**
	 * Writes out the list of Match objects as comma separated rows in a csv file at
	 * the given path
	 * 
	 * @param path    path to the CSV file
	 * @param matches list of matches to be written in the provided path
	 */
	public static void writeCsv(String path, List<Match> matches)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(path));
		String[] columnHeaders = new String[] { "GSTIN", "Date", "Bill no", "GST rate(%)", "Taxable value", "IGST",
				"CGST", "SGST", "Total", "MatchType", "GSTIN", "Date", "Bill no", "GST rate(%)", "Taxable value",
				"IGST", "CGST", "SGST", "Total" };
		List<String[]> content = new ArrayList<>();
		content.add(columnHeaders);
		for (Match match : matches) {
			String[] row = matchToStringArray(match);
			content.add(row);
		}
		writer.writeAll(content);
		writer.flush();
		writer.close();
	}

	private static String[] matchToStringArray(Match match) {
		String[] row = new String[19];
		if (match.getBuyer() != null) {
			row[0] = stringify(match.getBuyer().getGstin());
			row[1] = stringify(match.getBuyer().getDate());
			row[2] = stringify(match.getBuyer().getBillNo());
			row[3] = stringify(match.getBuyer().getGstRate());
			row[4] = stringify(match.getBuyer().getTaxableValue());
			row[5] = stringify(match.getBuyer().getIgst());
			row[6] = stringify(match.getBuyer().getCgst());
			row[7] = stringify(match.getBuyer().getSgst());
			row[8] = stringify(match.getBuyer().getTotal());
		}
		row[9] = stringify(match.getMatchType());

		if (match.getSupplier() != null) {
			row[10] = stringify(match.getSupplier().getGstin());
			row[11] = stringify(match.getSupplier().getDate());
			row[12] = stringify(match.getSupplier().getBillNo());
			row[13] = stringify(match.getSupplier().getGstRate());
			row[14] = stringify(match.getSupplier().getTaxableValue());
			row[15] = stringify(match.getSupplier().getIgst());
			row[16] = stringify(match.getSupplier().getCgst());
			row[17] = stringify(match.getSupplier().getSgst());
			row[18] = stringify(match.getSupplier().getTotal());
		}
		return row;
	}

	private static <T> String stringify(T t) {
		if (t == null)
			return null;
		return t.toString();
	}

}
