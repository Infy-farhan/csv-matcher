package demo.matcher.csvmatcher;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

@SpringBootTest
public class MatcherTest {
    @Test
    public void matcherTest(){
        String csvPathSupplier = "D:\\mdfarhan.khan\\workspace\\SE2 ID360 Craft\\SE2 Craft\\Supplier.csv";
		List<Transaction> suppliers = CsvFileUtil.readCsv(csvPathSupplier);
        String csvPathBuyer = "D:\\mdfarhan.khan\\workspace\\SE2 ID360 Craft\\SE2 Craft\\Buyer.csv";
		List<Transaction> buyers = CsvFileUtil.readCsv(csvPathBuyer);
        DifferenceFinder differenceFinder = DifferenceFinderByType.builder()
                                            .localDateDifferenceCalculator(new DateSimilarityCalculator(2))
                                            .numberDifferenceCalculator(new NumberSimilarityCalculator(2f))
                                            .stringDifferenceCalculator(new StringSimilarityCalculator())
                                            .build();  
        Matcher matcher = new SimilarityMatcher(differenceFinder);
        List<Match> matchList = matcher.compare(buyers, suppliers);
        matchList.forEach(System.out::println);
    }
}
