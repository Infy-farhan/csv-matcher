package demo.matcher.csvmatcher.matcher;

import java.util.ArrayList;
import java.util.List;

import demo.matcher.csvmatcher.model.Transaction;

public class SimilarityMatcher implements Matcher{
    private DifferenceFinder differenceFinder;

    public SimilarityMatcher(DifferenceFinder differenceFinder){
        this.differenceFinder = differenceFinder;
    }
    
    /**
     * {@inheritDoc}
     * Compares the lists by matching each buyer Transaction with all the suppliers Transactiona nd finding the closest match for it.
     */
    @Override
    public List<Match> compare(List<Transaction> buyers, List<Transaction> suppliers) {
        List<Match> matchList = new ArrayList<>();
        List<Transaction> matchedSellers = new ArrayList<>();
        for(Transaction buyer: buyers){
            float minDifferenceScore = Float.MAX_VALUE;
            Transaction mostSimilar = null;
            for(Transaction supplier: suppliers){
                float score = differenceFinder.getDifferenceScore(buyer, supplier);
                if(score<0) continue;
                if(score==0){
                    minDifferenceScore = 0;
                    mostSimilar = supplier;
                    break;
                }
                if(score<minDifferenceScore){
                    minDifferenceScore = score;
                    mostSimilar = supplier;
                }
            }
            Match match = new Match();
            match.setBuyer(buyer);
            if(minDifferenceScore == Float.MAX_VALUE){
                match.setMatchType(MatchType.NO_MATCH);
            }else{
                match.setSupplier(mostSimilar);
                match.setMatchType(minDifferenceScore==0? MatchType.EXACT : MatchType.PARTIAL);
                matchedSellers.add(mostSimilar);
            }
            matchList.add(match);
        }
        List<Transaction> unmatchedSellers = new ArrayList<>(suppliers);
        unmatchedSellers.removeAll(matchedSellers);
        for(Transaction seller: unmatchedSellers){
            Match match = new Match();
            match.setSupplier(seller);
            match.setMatchType(MatchType.NO_MATCH);
        }
        return matchList;
    }


}
