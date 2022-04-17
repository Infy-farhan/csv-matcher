package com.mfk.demo.matcher.scorer;

import com.mfk.demo.matcher.constant.Constant;
import com.mfk.demo.matcher.input.InputData;
import com.mfk.demo.matcher.model.Threshold;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Simple Date Similarity Calculator based on the difference in number of days
 */
@Component(Constant.DATE_SCORER)
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
    public Double getScore(InputData inputData, LocalDate source, LocalDate destination) {
        if (source == null && destination == null) {
            return 0.0;
        }
        if (source == null || destination == null) {
            return inputData.getNoOfColumns() + 1.0;
        }
        long difference = Math.abs(ChronoUnit.DAYS.between(source, destination));
        if (difference > inputData.getThreshold().getDateThreshold()){
            return inputData.getNoOfColumns() + 1.0;
        }
        return difference/ (double)inputData.getThreshold().getDateThreshold();
    }
}
