package com.mfk.demo.matcher.scorer;

import com.mfk.demo.matcher.constant.Constant;
import com.mfk.demo.matcher.input.InputData;
import org.springframework.stereotype.Component;

/**
 * Class provides concrete algorithm for calculating difference
 * score of two Double objects.
 */
@Component(Constant.NUMBER_SCORER)
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
    public Double getScore(InputData inputData, Double source, Double destination) {
        if (source == null) {
            source = 0.0;
        }
        if (destination == null) {
            destination = 0.0;
        }
        double difference = Math.abs(source - destination);
        if (difference > inputData.getThreshold().getNumberThreshold()) {
            return inputData.getNoOfColumns() + 1.0;
        }
        return difference/inputData.getThreshold().getNumberThreshold();
    }
}
