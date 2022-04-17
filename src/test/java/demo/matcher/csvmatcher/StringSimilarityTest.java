package demo.matcher.csvmatcher;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import demo.matcher.csvmatcher.similaritycalculator.StringSimilarityCalculator;

public class StringSimilarityTest {
    @Test
    public void testForNoMatch(){
        StringSimilarityCalculator calculator = new StringSimilarityCalculator();
        float dif = calculator.apply("Rams", "Raama");
        Assertions.assertEquals(-1f, dif);
    }

    @Test
    public void testForDiff(){
        StringSimilarityCalculator calculator = new StringSimilarityCalculator();
        float dif = calculator.apply("Ram", "Raama");
        Assertions.assertEquals(2, dif);
    }
}
