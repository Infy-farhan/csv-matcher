package com.mfk.demo.matcher.scorer;

import com.mfk.demo.matcher.constant.Constant;
import com.mfk.demo.matcher.input.InputData;
import com.mfk.demo.matcher.model.Threshold;
import com.mfk.demo.matcher.model.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * A Difference finder which calculates the difference score of two Transactions by
 * calculating the individual differences of each field whose difference in turn is
 * calculated by algorithm based on their data type.
 * The algorithms are represented by the BiFunctions stringDifferenceScorer,
 * numberDifferenceScorer and dateDifferenceScorer.
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

    /**
     * {@inheritDoc}
     * This method follows type based approach to calculate the difference score.
     * For all the Number fields it uses the numberDiffernceCalculator BiFunction
     * For all the String fields it uses the stringDiffernceCalculator BiFunction
     * For all the LocalDate fields it uses the localDateDiffernceCalculator BiFunction
     * Then it just adds up all the individual field differnce scores to find the difference score of the objects.
     * If any of the field difference is less than 0 (interpreted as no match) it returns -1 (meaning no match).
     */
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
