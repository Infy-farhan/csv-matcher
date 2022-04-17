package demo.matcher.csvmatcher;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import demo.matcher.csvmatcher.similaritycalculator.NumberSimilarityCalculator;

public class NumberSimilarityTest {
    @Test
    public void testForOverThresholdDiff(){
        NumberSimilarityCalculator calculator = new NumberSimilarityCalculator(10f);
        float dif = calculator.apply(20f, 32f);
        Assertions.assertEquals(-1f, dif);
    }

    @Test
    public void testForDiffOneDayUnderThreshold(){
        NumberSimilarityCalculator calculator = new NumberSimilarityCalculator(10f);
        float dif = calculator.apply(20f, 22f);
        Assertions.assertEquals(2f, dif);
    }

    @Test
    public void testForDiffOneDayExactThreshold(){
        NumberSimilarityCalculator calculator = new NumberSimilarityCalculator(10f);
        float dif = calculator.apply(20f, 30f);
        Assertions.assertEquals(10f, dif);
    }
}
