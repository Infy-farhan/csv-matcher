package com.mfk.demo.matcher.scorer;

import com.mfk.demo.matcher.constant.Constant;
import com.mfk.demo.matcher.input.InputData;
import com.mfk.demo.matcher.model.Threshold;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Class provides concrete algorithm for calculating difference
 * score of two Date objects.
 */
@Component(Constant.DATE_SCORER)
public class DateDifferenceScorer implements IDifferenceScorer<LocalDate> {


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
