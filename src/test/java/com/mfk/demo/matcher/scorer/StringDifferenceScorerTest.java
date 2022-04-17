package com.mfk.demo.matcher.scorer;

import com.mfk.demo.matcher.input.InputData;
import com.mfk.demo.matcher.model.Threshold;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;

public class StringDifferenceScorerTest {
    @InjectMocks
    StringDifferenceScorer stringDifferenceScorer;

    @Mock
    InputData inputData;

    @Mock
    Threshold threshold;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testValidateGetScoreReturnsZero(){
        Mockito.when(inputData.getNoOfColumns()).thenReturn(9);
        Mockito.when(inputData.getThreshold()).thenReturn(threshold);
        Mockito.when(threshold.getStringThreshold()).thenReturn(10l);
        Assertions.assertEquals(0.0, stringDifferenceScorer.getScore(inputData, "Some", "Some"));
    }

    @Test
    public void testValidateGetScoreReturnsPositiveDifferenceBelowThreshold(){
        Mockito.when(inputData.getNoOfColumns()).thenReturn(9);
        Mockito.when(inputData.getThreshold()).thenReturn(threshold);
        Mockito.when(threshold.getStringThreshold()).thenReturn(10l);
        Assertions.assertEquals(0.2, stringDifferenceScorer.getScore(inputData, "Some", "Comet"));
    }

    @Test
    public void testValidateGetScoreReturnsPositiveDifferenceExactThreshold(){
        Mockito.when(inputData.getNoOfColumns()).thenReturn(9);
        Mockito.when(inputData.getThreshold()).thenReturn(threshold);
        Mockito.when(threshold.getStringThreshold()).thenReturn(2l);
        Assertions.assertEquals(1.0, stringDifferenceScorer.getScore(inputData, "Some", "Comet"));
    }

    @Test
    public void testValidateGetScoreReturnsPositiveDifferenceOverThreshold(){
        Mockito.when(inputData.getNoOfColumns()).thenReturn(9);
        Mockito.when(inputData.getThreshold()).thenReturn(threshold);
        Mockito.when(threshold.getStringThreshold()).thenReturn(2l);
        Assertions.assertEquals(10.0, stringDifferenceScorer.getScore(inputData, "Some", "Comedy"));
    }
}
