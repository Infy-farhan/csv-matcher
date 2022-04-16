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

public class CsvFileUtil {

	public static List<Transaction> readCsv(String path) throws FileNotFoundException {
		List<Transaction> transactions = new CsvToBeanBuilder<Transaction>(new FileReader(path))
				.withSeparator(',')
				.withType(Transaction.class)
				.build()
				.parse();
		return transactions;
	}

	public static void writeCsv(String path, List<Match> matches)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		StatefulBeanToCsv<Match> beanWriter = new StatefulBeanToCsvBuilder<Match>(new FileWriter(path))
				.withSeparator(',')
				.build();
		beanWriter.write(matches);
	}
}
