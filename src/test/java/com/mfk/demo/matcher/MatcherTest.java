package com.mfk.demo.matcher;

public class MatcherTest {
    // @Test
    // public void matcherTest() throws FileNotFoundException{
    //     String csvPathSupplier = "D:\\mdfarhan.khan\\workspace\\SE2 ID360 Craft\\SE2 Craft\\Supplier.csv";
	// 	List<Transaction> suppliers = CsvFileReader.readCsv(csvPathSupplier);
    //     String csvPathBuyer = "D:\\mdfarhan.khan\\workspace\\SE2 ID360 Craft\\SE2 Craft\\Buyer.csv";
	// 	List<Transaction> buyers = CsvFileReader.readCsv(csvPathBuyer);
    //     DifferenceFinder differenceFinder = DifferenceFinderByType.builder()
    //                                         .localDateDifferenceCalculator(new DateSimilarityCalculator(2))
    //                                         .numberDifferenceCalculator(new NumberSimilarityCalculator(2f))
    //                                         .stringDifferenceCalculator(new StringSimilarityCalculator())
    //                                         .build();
    //     Matcher matcher = new SimilarityMatcher(differenceFinder);
    //     List<Match> matchList = matcher.compare(buyers, suppliers);
    //     matchList.forEach(System.out::println);
    //     Assertions.assertEquals(8, matchList.size());
    // }
}
