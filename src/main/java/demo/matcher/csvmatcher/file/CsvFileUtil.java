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

    public static List<Transaction> readCsv(String path){
    	List<Transaction> transactions = null;;
        try {
			transactions = new CsvToBeanBuilder<Transaction>(new FileReader(path))
													.withSeparator(',')
													.withType(Transaction.class)
													.build()
													.parse();
			
		} catch (IllegalStateException | FileNotFoundException e) {
			e.printStackTrace();
		}
        return transactions;
    }

	public static void writeCsv(String path, List<Match> matches){
		try {
			StatefulBeanToCsv<Match> beanWriter = new StatefulBeanToCsvBuilder<Match>(new FileWriter(path))
											.withSeparator(',')
											.build();
			beanWriter.write(matches);
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
