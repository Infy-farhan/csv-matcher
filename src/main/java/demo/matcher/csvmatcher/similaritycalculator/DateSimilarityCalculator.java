package demo.matcher.csvmatcher.similaritycalculator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.BiFunction;

public class DateSimilarityCalculator implements BiFunction<LocalDate, LocalDate, Float>{
    private int thresholdInDays;

    public DateSimilarityCalculator(int thresholdInDays){
        this.thresholdInDays = thresholdInDays;
    }

    @Override
    public Float apply(LocalDate t, LocalDate u) {
        if(t==null && u==null) return 0f;
        if(t==null || u==null) return -1f;
        long difference = Math.abs(ChronoUnit.DAYS.between(t, u));
        if(difference>thresholdInDays) return -1f;
        return (float)difference;
    }
    
}
