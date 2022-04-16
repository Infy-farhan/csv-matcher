package demo.matcher.csvmatcher.matcher;

import demo.matcher.csvmatcher.model.Transaction;

/**
 * Used by SimilarityMatcher to find the difference between two Transactions
 */
public interface DifferenceFinder {
    /**
     * Returns a float number representing the difference score for given two Transaction objects.
     * If the objects are exact match it returns 0
     * If the objects are partial match it returns a positive value representing the degree of their difference
     * If the objects do not matchthen it return -1
     * @param t1 first Transaction object
     * @param t2 second Transaction object
     * @return a float representing the difference score of the provided two transaction objects.
     */
    public Float getDifferenceScore(Transaction t1, Transaction t2);
}
