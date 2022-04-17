package com.mfk.demo.matcher.scorer;

import com.mfk.demo.matcher.input.InputData;
import com.mfk.demo.matcher.model.Threshold;

/**
 * Used by SimilarityMatcher to find the difference between two Transactions
 */
public interface IDifferenceScorer<T> {
    /**
     * Returns a float number representing the difference score for given two Transaction objects.
     * If the objects are exact match it returns 0
     * If the objects are partial match it returns a positive value representing the degree of their difference
     * If the objects do not matchthen it return -1
     * @param source first Transaction object
     * @param destination second Transaction object
     * @return  float representing the difference score of the provided two transaction objects.
     */
    public Double getScore(InputData inputData, T source, T destination);
}
