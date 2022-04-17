package demo.matcher.csvmatcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import demo.matcher.csvmatcher.io.IFileReader;
import demo.matcher.csvmatcher.io.IFileWriter;
import demo.matcher.csvmatcher.io.IInputScanner;
import demo.matcher.csvmatcher.io.InputData;
import demo.matcher.csvmatcher.matcher.DifferenceFinder;
import demo.matcher.csvmatcher.matcher.DifferenceFinderByType;
import demo.matcher.csvmatcher.matcher.Match;
import demo.matcher.csvmatcher.matcher.Matcher;
import demo.matcher.csvmatcher.matcher.SimilarityMatcher;
import demo.matcher.csvmatcher.model.Transaction;
import demo.matcher.csvmatcher.similaritycalculator.DateSimilarityCalculator;
import demo.matcher.csvmatcher.similaritycalculator.NumberSimilarityCalculator;
import demo.matcher.csvmatcher.similaritycalculator.StringSimilarityCalculator;

@SpringBootApplication
public class CsvMatcherApplication implements CommandLineRunner {
	@Autowired
	IInputScanner inputScanner;
	@Autowired
	IFileReader fileReader;
	@Autowired
	IFileWriter fileWriter;
	public static void main(String[] args) {
		SpringApplication.run(CsvMatcherApplication.class, args);
	}

	/**
	 * This method starts the execution of the matcher application.
	 * It takes input form the user and creates the matcher object based on the inputs.
	 * Then runs the matching algorithm and writes the result in an output file.
	 */
	@Override
	public void run(String... args) throws Exception{
		InputData inputData = inputScanner.getInputs();
		List<Transaction> buyers = fileReader.read(inputData.getBuyerPath());
		List<Transaction> suppliers = fileReader.read(inputData.getSupplierPath());
		DifferenceFinder differenceFinder = DifferenceFinderByType.builder()
				.localDateDifferenceCalculator(new DateSimilarityCalculator(inputData.getDateThreshold()))
				.numberDifferenceCalculator(new NumberSimilarityCalculator(inputData.getNumberThreshold()))
				.stringDifferenceCalculator(new StringSimilarityCalculator(inputData.getStringThreshold()))
				.build();
		Matcher matcher = new SimilarityMatcher(differenceFinder);
		List<Match> matchList = matcher.compare(buyers, suppliers);
		fileWriter.write(inputData.getResultPath(), matchList);
	}

}
