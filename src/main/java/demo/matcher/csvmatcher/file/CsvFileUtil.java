package demo.matcher.csvmatcher.file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import demo.matcher.csvmatcher.matcher.Match;
import demo.matcher.csvmatcher.model.Transaction;

/**
 * Provides utility methods to read and writes CSV files to and from java beans (Transaction objects)
 */
public class CsvFileUtil {

	/**
	 * Reads the contents of a CSV file, converts each row into a Transaction object and returns the list of Transaction objects
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
	 * Writes out the list of Match objects as comma separated rows in a csv file at the given path
	 * @param path path to the CSV file
	 * @param matches list of matches to be written in the provided path
	 */
	public static void writeCsv(String path, List<Match> matches)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		StatefulBeanToCsv<Match> beanWriter = new StatefulBeanToCsvBuilder<Match>(new FileWriter(path))
				.withSeparator(',')
				.build();
		beanWriter.write(matches);
	}
}
