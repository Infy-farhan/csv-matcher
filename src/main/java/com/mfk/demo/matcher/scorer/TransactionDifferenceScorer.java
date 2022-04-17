package com.mfk.demo.matcher.scorer;

import com.mfk.demo.matcher.model.Threshold;
import com.mfk.demo.matcher.model.Transaction;
import org.springframework.stereotype.Component;

/**
 * A Difference finder which calculates the difference score of two Transactions by
 * calculating the individual differences of each field whose difference in turn is
 * calculated by algorithm based on their data type.
 * The algorithms are represented by the BiFunctions stringDifferenceScorer,
 * numberDifferenceScorer and dateDifferenceScorer.
 */
@Component
public class TransactionDifferenceScorer implements IDifferenceScorer<Transaction> {

    private final NumberDifferenceScorer numberDifferenceScorer;
    private final StringDifferenceScorer stringDifferenceScorer;
    private final DateDifferenceScorer dateDifferenceScorer;

    public TransactionDifferenceScorer(NumberDifferenceScorer numberDifferenceScorer,
                                       StringDifferenceScorer stringDifferenceScorer,
                                       DateDifferenceScorer dateDifferenceScorer) {
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
    public Double getScore(Threshold threshold, Transaction t1, Transaction t2) {
        return 0.0;
    }

}
