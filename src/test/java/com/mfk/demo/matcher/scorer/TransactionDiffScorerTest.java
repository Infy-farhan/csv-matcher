package com.mfk.demo.matcher.scorer;

import com.mfk.demo.matcher.input.InputData;
import com.mfk.demo.matcher.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TransactionDiffScorerTest {

    @InjectMocks
    private TransactionDifferenceScorer transactionDifferenceScorer;

    @Mock
    private IDifferenceScorer numberDifferenceScorer;

    @Mock
    private IDifferenceScorer dateDifferenceScorer;

    @Mock
    private IDifferenceScorer stringDifferenceScorer;

    @Mock
    private InputData inputData;

    @Mock
    private Transaction transaction;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testValidateGetScoreReturns() {
        Mockito.when(numberDifferenceScorer.getScore(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any()))
                .thenReturn(10.0);
        Mockito.when(dateDifferenceScorer.getScore(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any()))
                .thenReturn(10.0);
        Mockito.when(stringDifferenceScorer.getScore(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any()))
                .thenReturn(10.0);
        Mockito.when(inputData.getNoOfColumns()).thenReturn(9);
        Assertions.assertEquals(10.0,transactionDifferenceScorer.getScore(inputData,transaction,transaction));
    }

    @Test
    public void testValidateGetScoreReturnsZero() {
        Mockito.when(numberDifferenceScorer.getScore(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any()))
                .thenReturn(0.0);
        Mockito.when(dateDifferenceScorer.getScore(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any()))
                .thenReturn(0.0);
        Mockito.when(stringDifferenceScorer.getScore(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any()))
                .thenReturn(0.0);
        Mockito.when(inputData.getNoOfColumns()).thenReturn(9);
        Assertions.assertEquals(0.0,transactionDifferenceScorer.getScore(inputData,transaction,transaction));
    }
}
