package demo.matcher.csvmatcher;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import demo.matcher.csvmatcher.similaritycalculator.StringSimilarityCalculator;

@SpringBootTest
public class StringSimilarityTest {
    @Test
    public void testForOverThresholdDiff(){
        StringSimilarityCalculator calculator = new StringSimilarityCalculator();
        float dif = calculator.apply("Rams", "Raama");
        Assertions.assertEquals(-1f, dif);
    }

    @Test
    public void testForDiffOneDayUnderThreshold(){
        StringSimilarityCalculator calculator = new StringSimilarityCalculator();
        float dif = calculator.apply("Rams", "Raama");
        Assertions.assertEquals(3, dif);
    }

    @Test
    public void testForDiffOneDayExactThreshold(){
        StringSimilarityCalculator calculator = new StringSimilarityCalculator();
        float dif = calculator.apply("Ram", "Raama");
        Assertions.assertEquals(-1f, dif);
    }

    @Test
    public void testForNoDiffExactThreshold(){
        StringSimilarityCalculator calculator = new StringSimilarityCalculator();
        float dif = calculator.apply("Ram", "Ram");
        Assertions.assertEquals(0.0f, dif);
    }
}
