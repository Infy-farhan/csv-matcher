package com.mfk.demo.matcher.matcher;

import com.mfk.demo.matcher.model.Threshold;
import com.mfk.demo.matcher.model.Transaction;
import com.mfk.demo.matcher.scorer.TransactionDifferenceScorer;
import org.springframework.stereotype.Component;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
        Set<MatchPair> matchPairSet = generateSortedMatchPairSet(threshold, buyers, suppliers);
        List<Match> matchList = new ArrayList<>();
        Set<Integer> matchedBuyerIndices = new HashSet<>();
        Set<Integer> matechedSupplierIndices = new HashSet<>();
        for(MatchPair pair: matchPairSet){
            if(matchedBuyerIndices.contains(pair.getBuyerIndex()) || matechedSupplierIndices.contains(pair.getSupplierIndex())){
                continue;
            }
            if(pair.getDifferenceScore()==0.0){
                Match match = new Match(buyers.get(pair.getBuyerIndex()), MatchType.EXACT, suppliers.get(pair.getSupplierIndex()));
                matchList.add(match);
            }else if(pair.getDifferenceScore()<=1){
                Match match = new Match(buyers.get(pair.getBuyerIndex()), MatchType.PARTIAL, suppliers.get(pair.getSupplierIndex()));
                matchList.add(match);
            }else{
                Match noMatch1 = new Match(buyers.get(pair.getBuyerIndex()), MatchType.NO_MATCH, null);
                Match noMatch2 = new Match(null, MatchType.NO_MATCH, suppliers.get(pair.getSupplierIndex()));
                matchList.add(noMatch1);
                matchList.add(noMatch2);
            }
            matchedBuyerIndices.add(pair.getBuyerIndex());
            matechedSupplierIndices.add(pair.getSupplierIndex());
        }
        return matchList;
    }

    private Set<MatchPair> generateSortedMatchPairSet(Threshold threshold, List<Transaction> buyers, List<Transaction> suppliers){
        Set<MatchPair> treeSet = new TreeSet<>();
        for(int i=0; i<buyers.size(); i++){
            for(int j=0; j<suppliers.size(); j++){
                double score = transactionDifferenceScorer.getScore(threshold, buyers.get(i), suppliers.get(j));
                treeSet.add(new MatchPair(i, j, score));
            }
        }
        return treeSet;
    }

    @Getter
    public static class MatchPair implements Comparable<MatchPair>{
        private final int buyerIndex;
        private final int supplierIndex;
        private final double differenceScore;

        public MatchPair(int buyerIndex, int supplierIndex, double differenceScore) {
            this.buyerIndex = buyerIndex;
            this.supplierIndex = supplierIndex;
            this.differenceScore = differenceScore;
        }

        @Override
        public int compareTo(MatchPair o) {
            int scoreCompare = Double.compare(this.getDifferenceScore(), o.getDifferenceScore());
            if(scoreCompare==0){
                int buyerCompare = Integer.compare(this.getBuyerIndex(), o.getBuyerIndex());
                if(buyerCompare==0) return Integer.compare(this.getSupplierIndex(), o.getSupplierIndex());
                return buyerCompare;
            }
            return scoreCompare;
        }

        @Override
        public boolean equals(Object o){
            if(o instanceof MatchPair){
                MatchPair other = (MatchPair) o;
                return this.getBuyerIndex()==other.getBuyerIndex() && this.getSupplierIndex()==this.getSupplierIndex();
            }
            return false;
        }

    }

}
