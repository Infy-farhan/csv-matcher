package com.mfk.demo.matcher.scorer;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mfk.demo.matcher.input.InputData;
import com.mfk.demo.matcher.model.Threshold;

public class NumberDiffScorerTest {
    @InjectMocks
    NumberDifferenceScorer numberDifferenceScorer;

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
        Mockito.when(threshold.getNumberThreshold()).thenReturn(10.0);
        Assertions.assertEquals(0.0, numberDifferenceScorer.getScore(inputData, 10.0, 10.0));
    }

    @Test
    public void testValidateGetScoreReturnsPositiveDifferenceBelowThreshold(){
        Mockito.when(inputData.getNoOfColumns()).thenReturn(9);
        Mockito.when(inputData.getThreshold()).thenReturn(threshold);
        Mockito.when(threshold.getNumberThreshold()).thenReturn(10.0);
        Assertions.assertEquals(0.2, numberDifferenceScorer.getScore(inputData, 2.0, 4.0));
    }

    @Test
    public void testValidateGetScoreReturnsPositiveDifferenceExactThreshold(){
        Mockito.when(inputData.getNoOfColumns()).thenReturn(9);
        Mockito.when(inputData.getThreshold()).thenReturn(threshold);
        Mockito.when(threshold.getNumberThreshold()).thenReturn(2.0);
        Assertions.assertEquals(1.0, numberDifferenceScorer.getScore(inputData, 2.0, 4.0));
    }

    @Test
    public void testValidateGetScoreReturnsPositiveDifferenceOverThreshold(){
        Mockito.when(inputData.getNoOfColumns()).thenReturn(9);
        Mockito.when(inputData.getThreshold()).thenReturn(threshold);
        Mockito.when(threshold.getNumberThreshold()).thenReturn(2.0);
        Assertions.assertEquals(10.0, numberDifferenceScorer.getScore(inputData, 1.0, 5.5));
    }
}
