package com.mfk.demo.matcher.scorer;

import com.mfk.demo.matcher.model.Threshold;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Simple Date Similarity Calculator based on the difference in number of days
 */
@Component
public class DateDifferenceScorer implements IDifferenceScorer<LocalDate> {

    /**
     * Returns the differnce score of two LocalDate objects.
     * The difference score is the number of days between the two dates
     *
     * @param source
     * @param destination
     * @return The difference score of the two localdate
     */
    @Override
    public Double getScore(Threshold threshold, LocalDate source, LocalDate destination) {
        if (source == null && destination == null) {
            return 0.0;
        }
        if (source == null || destination == null) {
            return -1.0;
        }
        long difference = Math.abs(ChronoUnit.DAYS.between(source, destination));
        if (difference > threshold.getDateThreshold()){
            return -1.0;
        }
        return difference/ (double)threshold.getDateThreshold();
    }
}
