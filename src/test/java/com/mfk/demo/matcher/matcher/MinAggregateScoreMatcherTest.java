package com.mfk.demo.matcher.matcher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mfk.demo.matcher.input.InputData;
import com.mfk.demo.matcher.model.Transaction;
import com.mfk.demo.matcher.scorer.TransactionDifferenceScorer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MinAggregateScoreMatcherTest {
    @InjectMocks
    MinAggregateScoreMatcher matcher;

    @Mock
    TransactionDifferenceScorer transactionDifferenceScorer;

    @Mock
    InputData inputData;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testValidateExactAndPartialMatch(){
        List<Transaction> buyers = getBuyers();
        List<Transaction> suppliers = getSuppliers();
        Mockito.when(transactionDifferenceScorer.getScore(inputData, buyers.get(0), suppliers.get(0))).thenReturn(0.0);
        Mockito.when(transactionDifferenceScorer.getScore(inputData, buyers.get(0), suppliers.get(1))).thenReturn(10.0);
        Mockito.when(transactionDifferenceScorer.getScore(inputData, buyers.get(1), suppliers.get(0))).thenReturn(10.0);
        Mockito.when(transactionDifferenceScorer.getScore(inputData, buyers.get(1), suppliers.get(1))).thenReturn(0.5);
        List<Match> matches = matcher.match(inputData, buyers, suppliers);
        Assertions.assertEquals(MatchType.EXACT, matches.get(0).getMatchType());
        Assertions.assertEquals(MatchType.PARTIAL, matches.get(1).getMatchType());
    }

    public List<Transaction> getBuyers(){
        List<Transaction> buyers = new ArrayList<>();
        Transaction t1 = new Transaction();
        t1.setGstin("29AAACB2894G1ZJ");
        t1.setDate(LocalDate.of(2017, 9, 3));
        t1.setBillNo("474330129"); 
        t1.setGstRate(18.0);
        t1.setTaxableValue(2099.0);
        t1.setCgst(188.91);
        t1.setSgst(188.91);
        t1.setTotal(2476.82);
        buyers.add(t1);

        Transaction t2 = new Transaction();
        t2.setGstin("29ACWPJ0559R1ZH");
        t2.setDate(LocalDate.of(2017, 7, 1));
        t2.setBillNo(""); 
        t2.setGstRate(18.0);
        t2.setTaxableValue(44100.0);
        t2.setCgst(3969.0);
        t2.setSgst(3969.0);
        t2.setTotal(52038.0);
        buyers.add(t2);
        return buyers;
    } 

    public List<Transaction> getSuppliers(){
        List<Transaction> suppliers = new ArrayList<>();
        Transaction t1 = new Transaction();
        t1.setGstin("29AAACB2894G1ZJ");
        t1.setDate(LocalDate.of(2017, 9, 3));
        t1.setBillNo("474330129"); 
        t1.setGstRate(18.0);
        t1.setTaxableValue(2099.0);
        t1.setCgst(188.91);
        t1.setSgst(188.91);
        t1.setTotal(2476.82);
        suppliers.add(t1);

        Transaction t2 = new Transaction();
        t2.setGstin("29ACWPJ0559R1ZH");
        t2.setDate(LocalDate.of(2017, 7, 1));
        t2.setBillNo("3"); 
        t2.setGstRate(18.0);
        t2.setTaxableValue(44100.0);
        t2.setCgst(3969.0);
        t2.setSgst(3969.0);
        t2.setTotal(52038.0);
        suppliers.add(t2);
        return suppliers;
    } 


}
