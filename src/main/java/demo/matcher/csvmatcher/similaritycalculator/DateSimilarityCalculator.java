package demo.matcher.csvmatcher.similaritycalculator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.BiFunction;

/**
 * Simple Date Similarity Calculator based on the difference in number of days
 */
public class DateSimilarityCalculator implements BiFunction<LocalDate, LocalDate, Float>{
    private int thresholdInDays;

    public DateSimilarityCalculator(int thresholdInDays){
        this.thresholdInDays = thresholdInDays;
    }

    /**
     * Returns the differnce score of two LocalDate objects.
     * The difference score is the number of days between the two dates
     * @param t 
     * @param u
     * @return The difference score of the two localdate
     */
    @Override
    public Float apply(LocalDate t, LocalDate u) {
        if(t==null && u==null) return 0f;
        if(t==null || u==null) return -1f;
        long difference = Math.abs(ChronoUnit.DAYS.between(t, u));
        if(difference>thresholdInDays) return -1f;
        return (float)difference;
    }
    
}
