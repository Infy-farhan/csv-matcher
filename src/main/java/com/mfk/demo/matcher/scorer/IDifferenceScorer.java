package com.mfk.demo.matcher.scorer;

import com.mfk.demo.matcher.input.InputData;
import com.mfk.demo.matcher.model.Threshold;

/**
 * Contract defining interface for difference scoring algorithms.
 *
 * All concrete difference scoring algorithm implement this interface
 * and define specific algorithm for difference scoring.
 *
 * @param <T>
 */
public interface IDifferenceScorer<T> {

    /**
     * Find and returns the difference score for difference between
     * passed source and destination objects.
     *
     * @param inputData
     * @param source
     * @param destination
     * @return
     */
    Double getScore(InputData inputData, T source, T destination);
}
