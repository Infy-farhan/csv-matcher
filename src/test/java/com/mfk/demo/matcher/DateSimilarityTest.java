package com.mfk.demo.matcher;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mfk.demo.matcher.scorer.DateDifferenceScorer;

public class DateSimilarityTest {
    @Test
    public void testForOverThresholdDiff(){
        LocalDate date1 = LocalDate.of(2018, 01, 01);
        LocalDate date2 = LocalDate.of(2018, 01, 03);
        DateDifferenceScorer calculator = new DateDifferenceScorer(1);
        float dif = calculator.apply(date1, date2);
        Assertions.assertEquals(-1f, dif);
    }

    @Test
    public void testForDiffOneDayUnderThreshold(){
        LocalDate date1 = LocalDate.of(2018, 01, 01);
        LocalDate date2 = LocalDate.of(2018, 01, 02);
        DateDifferenceScorer calculator = new DateDifferenceScorer(2);
        float dif = calculator.apply(date1, date2);
        Assertions.assertEquals(1f, dif);
    }

    @Test
    public void testForDiffOneDayExactThreshold(){
        LocalDate date1 = LocalDate.of(2018, 01, 01);
        LocalDate date2 = LocalDate.of(2018, 01, 02);
        DateDifferenceScorer calculator = new DateDifferenceScorer(1);
        float dif = calculator.apply(date1, date2);
        Assertions.assertEquals(1f, dif);
    }
}
