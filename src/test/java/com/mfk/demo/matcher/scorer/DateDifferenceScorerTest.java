package com.mfk.demo.matcher.scorer;

import java.time.LocalDate;

import com.mfk.demo.matcher.input.InputData;
import com.mfk.demo.matcher.model.Threshold;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class DateDifferenceScorerTest {
    @InjectMocks
    DateDifferenceScorer dateDifferenceScorer;

    @Mock
    InputData inputData;

    @Mock
    Threshold threshold;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testValidateGetScoreReturnsZero(){
        Mockito.when(inputData.getNoOfColumns()).thenReturn(9);
        Mockito.when(inputData.getThreshold()).thenReturn(threshold);
        Mockito.when(threshold.getDateThreshold()).thenReturn(2l);
        LocalDate day1 = LocalDate.of(2022, 4, 17);
        LocalDate day2 = LocalDate.of(2022, 4, 17);
        Assertions.assertEquals(0.0, dateDifferenceScorer.getScore(inputData, day1, day2));
    }

    @Test
    public void testValidateGetScoreReturnsPositiveDifferenceBelowThreshold(){
        Mockito.when(inputData.getNoOfColumns()).thenReturn(9);
        Mockito.when(inputData.getThreshold()).thenReturn(threshold);
        Mockito.when(threshold.getDateThreshold()).thenReturn(2l);
        LocalDate day1 = LocalDate.of(2022, 4, 18);
        LocalDate day2 = LocalDate.of(2022, 4, 17);
        Assertions.assertEquals(0.5, dateDifferenceScorer.getScore(inputData, day1, day2));
    }

    @Test
    public void testValidateGetScoreReturnsPositiveDifferenceExactThreshold(){
        Mockito.when(inputData.getNoOfColumns()).thenReturn(9);
        Mockito.when(inputData.getThreshold()).thenReturn(threshold);
        Mockito.when(threshold.getDateThreshold()).thenReturn(2l);
        LocalDate day1 = LocalDate.of(2022, 4, 18);
        LocalDate day2 = LocalDate.of(2022, 4, 16);
        Assertions.assertEquals(1.0, dateDifferenceScorer.getScore(inputData, day1, day2));
    }

    @Test
    public void testValidateGetScoreReturnsPositiveDifferenceOverThreshold(){
        Mockito.when(inputData.getNoOfColumns()).thenReturn(9);
        Mockito.when(inputData.getThreshold()).thenReturn(threshold);
        Mockito.when(threshold.getDateThreshold()).thenReturn(2l);
        LocalDate day1 = LocalDate.of(2023, 4, 18);
        LocalDate day2 = LocalDate.of(2022, 4, 16);
        Assertions.assertEquals(10.0, dateDifferenceScorer.getScore(inputData, day1, day2));
    }
}
