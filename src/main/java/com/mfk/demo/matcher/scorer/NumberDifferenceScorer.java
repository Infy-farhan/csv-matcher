package com.mfk.demo.matcher.scorer;

import com.mfk.demo.matcher.model.Threshold;
import org.springframework.stereotype.Component;

/**
 * Simple Number Similarity Calculator based on the absolute difference
 */
@Component
public class NumberDifferenceScorer implements IDifferenceScorer<Double> {

    /**
     * Returns the differnce score of two floats.
     * The difference score is the absolute difference between the two floats
     *
     * @param source
     * @param destination
     * @return The difference score of the two floats
     */
    @Override
    public Double getScore(Threshold threshold, Double source, Double destination) {
        if (source == null) {
            source = 0.0;
        }
        if (destination == null) {
            destination = 0.0;
        }
        double difference = Math.abs(source - destination);
        if (difference > threshold.getNumberThreshold()) {
            return -1.0;
        }
        return difference/threshold.getNumberThreshold();
    }
}
