package com.mfk.demo.matcher.matcher;

import com.mfk.demo.matcher.model.Threshold;
import com.mfk.demo.matcher.model.Transaction;
import com.mfk.demo.matcher.scorer.TransactionDifferenceScorer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimilarityMatcher implements IMatcher {

    private final TransactionDifferenceScorer transactionDifferenceScorer;

    public SimilarityMatcher(TransactionDifferenceScorer transactionDifferenceScorer) {
        this.transactionDifferenceScorer = transactionDifferenceScorer;
    }


    /**
     * {@inheritDoc}
     * Compares the lists by matching each buyer Transaction with all the suppliers Transactiona nd finding the closest match for it.
     */
    @Override
    public List<Match> match(Threshold threshold, List<Transaction> buyers, List<Transaction> suppliers) {
        List<Match> matchList = new ArrayList<>();
        for (Transaction buyer : buyers) {
            double minDifferenceScore = Double.MAX_VALUE;
            Transaction mostSimilar = null;
            for (Transaction supplier : suppliers) {
                double score = transactionDifferenceScorer.getScore(threshold, buyer, supplier);
                if (score < 0) continue;
                if (score == 0) {
                    minDifferenceScore = 0;
                    mostSimilar = supplier;
                    break;
                }
                if (score < minDifferenceScore) {
                    minDifferenceScore = score;
                    mostSimilar = supplier;
                }
            }
            Match match = new Match();
            match.setBuyer(buyer);
            if (minDifferenceScore == Double.MAX_VALUE) {
                match.setMatchType(MatchType.NO_MATCH);
            } else {
                match.setSupplier(mostSimilar);
                match.setMatchType(minDifferenceScore == 0 ? MatchType.EXACT : MatchType.PARTIAL);
                suppliers.remove(mostSimilar);
            }
            matchList.add(match);
        }
        for (Transaction supplier : suppliers) {
            Match match = new Match();
            match.setSupplier(supplier);
            match.setMatchType(MatchType.NO_MATCH);
            matchList.add(match);
        }
        return matchList;
    }


}
