package com.mfk.demo.matcher.matcher;

import com.mfk.demo.matcher.constant.Constant;
import com.mfk.demo.matcher.input.InputData;
import com.mfk.demo.matcher.model.Threshold;
import com.mfk.demo.matcher.model.Transaction;
import com.mfk.demo.matcher.scorer.IDifferenceScorer;
import com.mfk.demo.matcher.scorer.TransactionDifferenceScorer;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Concrete implementation if Matching algorithm to match
 * row from buyer csv to seller csv.
 *
 * This matching algorithm makes use of sorted difference
 * score. And matches the rows with least score first.
 *
 */
@Component
public class MinAggregateScoreMatcher implements IMatcher {

    private final IDifferenceScorer transactionDifferenceScorer;

    public MinAggregateScoreMatcher(
            @Qualifier(Constant.TRANSACTION_SCORER) IDifferenceScorer transactionDifferenceScorer) {
        this.transactionDifferenceScorer = transactionDifferenceScorer;
    }

    @Override
    public List<Match> match(InputData inputData, List<Transaction> buyers, List<Transaction> suppliers) {

        Set<MatchPair> matchPairSet = generateSortedMatchPairSet(inputData, buyers, suppliers);
        List<Match> matchList = new ArrayList<>();
        Set<Integer> matchedBuyerIndices = new HashSet<>();
        Set<Integer> matchedSupplierIndices = new HashSet<>();

        for (MatchPair pair : matchPairSet) {
            if (matchedBuyerIndices.contains(pair.getBuyerIndex())
                    || matchedSupplierIndices.contains(pair.getSupplierIndex()) || pair.getDifferenceScore() > 1) {
                continue;
            }
            if (pair.getDifferenceScore() == 0.0) {
                Match match = new Match(buyers.get(pair.getBuyerIndex()), MatchType.EXACT,
                        suppliers.get(pair.getSupplierIndex()));
                matchList.add(match);
            } else if (pair.getDifferenceScore() <= 1) {
                Match match = new Match(buyers.get(pair.getBuyerIndex()), MatchType.PARTIAL,
                        suppliers.get(pair.getSupplierIndex()));
                matchList.add(match);
            }
            matchedBuyerIndices.add(pair.getBuyerIndex());
            matchedSupplierIndices.add(pair.getSupplierIndex());
        }
        for (int buyerIndex = 0; buyerIndex < buyers.size(); buyerIndex++) {
            if (!matchedBuyerIndices.contains(buyerIndex)) {
                Match onlyInSeller = new Match(buyers.get(buyerIndex), MatchType.NO_MATCH, null);
                matchList.add(onlyInSeller);
            }
        }
        for (int supplierIndex = 0; supplierIndex < suppliers.size(); supplierIndex++) {
            if (!matchedSupplierIndices.contains(supplierIndex)) {
                Match onlyInSeller = new Match(null, MatchType.NO_MATCH, suppliers.get(supplierIndex));
                matchList.add(onlyInSeller);
            }
        }
        return matchList;
    }

    private Set<MatchPair> generateSortedMatchPairSet(InputData inputData, List<Transaction> buyers,
            List<Transaction> suppliers) {
        Set<MatchPair> treeSet = new TreeSet<>();
        for (int buyerIndex = 0; buyerIndex < buyers.size(); buyerIndex++) {
            for (int supplierIndex = 0; supplierIndex < suppliers.size(); supplierIndex++) {
                double score = transactionDifferenceScorer.getScore(inputData, buyers.get(buyerIndex),
                        suppliers.get(supplierIndex));
                treeSet.add(new MatchPair(buyerIndex, supplierIndex, score));
            }
        }
        return treeSet;
    }

    @Getter
    public static class MatchPair implements Comparable<MatchPair> {

        private final int buyerIndex;
        private final int supplierIndex;
        private final double differenceScore;

        public MatchPair(int buyerIndex, int supplierIndex, double differenceScore) {
            this.buyerIndex = buyerIndex;
            this.supplierIndex = supplierIndex;
            this.differenceScore = differenceScore;
        }

        @Override
        public int compareTo(MatchPair matchPair) {
            int scoreCompare = Double.compare(this.getDifferenceScore(), matchPair.getDifferenceScore());
            if (scoreCompare == 0) {
                int buyerCompare = Integer.compare(this.getBuyerIndex(), matchPair.getBuyerIndex());
                if (buyerCompare == 0)
                    return Integer.compare(this.getSupplierIndex(), matchPair.getSupplierIndex());
                return buyerCompare;
            }
            return scoreCompare;
        }

        @Override
        public boolean equals(Object object) {
            if (object instanceof MatchPair) {
                MatchPair other = (MatchPair) object;
                return this.getBuyerIndex() == other.getBuyerIndex()
                        && this.getSupplierIndex() == this.getSupplierIndex();
            }
            return false;
        }

    }

}
