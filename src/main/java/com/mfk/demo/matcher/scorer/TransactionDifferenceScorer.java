package com.mfk.demo.matcher.scorer;

import com.mfk.demo.matcher.constant.Constant;
import com.mfk.demo.matcher.input.InputData;
import com.mfk.demo.matcher.model.Threshold;
import com.mfk.demo.matcher.model.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Class provides concrete algorithm for calculating difference
 * score of two Transaction objects.
 */
@Component(Constant.TRANSACTION_SCORER)
public class TransactionDifferenceScorer implements IDifferenceScorer<Transaction> {

    private final IDifferenceScorer numberDifferenceScorer;
    private final IDifferenceScorer stringDifferenceScorer;
    private final IDifferenceScorer dateDifferenceScorer;

    public TransactionDifferenceScorer(@Qualifier(Constant.NUMBER_SCORER) IDifferenceScorer numberDifferenceScorer,
                                       @Qualifier(Constant.STRING_SCORER)IDifferenceScorer stringDifferenceScorer,
                                       @Qualifier(Constant.DATE_SCORER) IDifferenceScorer dateDifferenceScorer) {
        this.numberDifferenceScorer = numberDifferenceScorer;
        this.stringDifferenceScorer = stringDifferenceScorer;
        this.dateDifferenceScorer = dateDifferenceScorer;
    }

    @Override
    public Double getScore(InputData inputData, Transaction source, Transaction destination) {
        double aggregateScore = 
            stringDifferenceScorer.getScore(inputData, source.getGstin(), destination.getGstin())
            + dateDifferenceScorer.getScore(inputData, source.getDate(), destination.getDate())
            + stringDifferenceScorer.getScore(inputData, source.getBillNo(), destination.getBillNo())
            + numberDifferenceScorer.getScore(inputData, source.getGstRate(), destination.getGstRate())
            + numberDifferenceScorer.getScore(inputData, source.getTaxableValue(), destination.getTaxableValue())
            + numberDifferenceScorer.getScore(inputData, source.getIgst(), destination.getIgst())
            + numberDifferenceScorer.getScore(inputData, source.getCgst(), destination.getCgst())
            + numberDifferenceScorer.getScore(inputData, source.getSgst(), destination.getSgst())
            + numberDifferenceScorer.getScore(inputData, source.getTotal(), destination.getTotal());
        return aggregateScore/inputData.getNoOfColumns();
    }

}
