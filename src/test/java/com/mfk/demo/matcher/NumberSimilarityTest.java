package com.mfk.demo.matcher;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mfk.demo.matcher.scorer.NumberDifferenceScorer;

public class NumberSimilarityTest {
    @Test
    public void testForOverThresholdDiff(){
        NumberDifferenceScorer calculator = new NumberDifferenceScorer(10f);
        float dif = calculator.apply(20f, 32f);
        Assertions.assertEquals(-1f, dif);
    }

    @Test
    public void testForDiffOneDayUnderThreshold(){
        NumberDifferenceScorer calculator = new NumberDifferenceScorer(10f);
        float dif = calculator.apply(20f, 22f);
        Assertions.assertEquals(2f, dif);
    }

    @Test
    public void testForDiffOneDayExactThreshold(){
        NumberDifferenceScorer calculator = new NumberDifferenceScorer(10f);
        float dif = calculator.apply(20f, 30f);
        Assertions.assertEquals(10f, dif);
    }
}
