package demo.matcher.csvmatcher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import demo.matcher.csvmatcher.file.CsvFileUtil;
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

	public static void main(String[] args) {
		SpringApplication.run(CsvMatcherApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter buyer.csv path: ");
		String buyerPath = in.nextLine();
		System.out.print("Enter supplier.csv path: ");
		String supplierPath = in.nextLine();
		System.out.print("Enter output result path: ");
		String resultPath = in.nextLine();
		float numberThreshold;
		int dateThreshold;
		while(true){
			System.out.println(
				"Input individual space separated thresholds for number(float) and date (int in days)");
			String[] thresholds = in.nextLine().split(" ");
			if(thresholds.length==2){
				numberThreshold = Float.parseFloat(thresholds[0]);
				dateThreshold = Integer.parseInt(thresholds[1]);
				break;
			}else{
				System.err.println("Wrong number of inputs. Please input three space separated values");
			}
		}
		in.close();
		List<Transaction> suppliers;
		try {
			suppliers = CsvFileUtil.readCsv(supplierPath);
		} catch (FileNotFoundException e) {
			System.err.println("Wrong path for Suppliers. File not found");
			return;
		}
		List<Transaction> buyers;
		try {
			buyers = CsvFileUtil.readCsv(buyerPath);
		} catch (FileNotFoundException e) {
			System.err.println("Wrong path for Buyers. File not found");
			return;
		}
		DifferenceFinder differenceFinder = DifferenceFinderByType.builder()
				.localDateDifferenceCalculator(new DateSimilarityCalculator(dateThreshold))
				.numberDifferenceCalculator(new NumberSimilarityCalculator(numberThreshold))
				.stringDifferenceCalculator(new StringSimilarityCalculator())
				.build();
		Matcher matcher = new SimilarityMatcher(differenceFinder);
		List<Match> matchList = matcher.compare(buyers, suppliers);
		System.out.println(matchList);
		try {
			CsvFileUtil.writeCsv(resultPath, matchList);
		} catch (IOException e) {
			System.err.println("Could not write to the provided output file." + e.getMessage());
		}
	}

}
