package demo.matcher.csvmatcher.matcher;

import java.time.LocalDate;
import java.util.function.BiFunction;

import demo.matcher.csvmatcher.model.Transaction;
import lombok.Builder;
import lombok.NonNull;

@Builder
/**
 * A Difference finder which calculates the difference score of two Transactions by calculating the individual differences of each field whose difference in turn is calculated by algorithm based on their data type.
 * The algorithms are represented by the BiFunctions stringDifferenceCalculator, numberDifferenceCalculator and localDateDifferenceCalculator.
 */
public class DifferenceFinderByType implements DifferenceFinder{
    @NonNull
    private BiFunction<String, String, Float> stringDifferenceCalculator;
    @NonNull
    private BiFunction<Float, Float, Float> numberDifferenceCalculator;
    @NonNull
    private BiFunction<LocalDate, LocalDate, Float> localDateDifferenceCalculator; 

    /**
     * {@inheritDoc}
     * This method follows type based approach to calculate the difference score.
     * For all the Number fields it uses the numberDiffernceCalculator BiFunction
     * For all the String fields it uses the stringDiffernceCalculator BiFunction
     * For all the LocalDate fields it uses the localDateDiffernceCalculator BiFunction
     * Then it just adds up all the individual field differnce scores to find the difference score of the objects.
     * If any of the field difference is less than 0 (interpreted as no match) it returns -1 (meaning no match).
     */
    @Override
    public Float getDifferenceScore(Transaction t1, Transaction t2) {
        float gstinDif = stringDifferenceCalculator.apply(t1.getGstin(), t2.getGstin());
        if(gstinDif<0) return -1f;
        float dateDif = localDateDifferenceCalculator.apply(t1.getDate(), t2.getDate());
        if(dateDif<0) return -1f;
        float billNoDif = stringDifferenceCalculator.apply(t1.getBillNo(), t2.getBillNo());
        if(billNoDif<0) return -1f;
        float gstRateDif = numberDifferenceCalculator.apply(t1.getGstRate(), t2.getGstRate());
        if(gstRateDif<0) return -1f;
        float taxableValueDif = numberDifferenceCalculator.apply(t1.getTaxableValue(), t2.getTaxableValue());
        if(taxableValueDif<0) return -1f;
        float igstDif = numberDifferenceCalculator.apply(t1.getIgst(), t2.getIgst());
        if(igstDif<0) return -1f;
        float cgstDif = numberDifferenceCalculator.apply(t1.getCgst(), t2.getCgst());
        if(cgstDif<0) return -1f;
        float sgstDif = numberDifferenceCalculator.apply(t1.getSgst(), t2.getSgst());
        if(sgstDif<0) return -1f;
        float totalDif = numberDifferenceCalculator.apply(t1.getIgst(), t2.getIgst());
        if(totalDif<0) return -1f;
        return gstinDif + dateDif + billNoDif + gstRateDif + taxableValueDif + igstDif + cgstDif + sgstDif + totalDif;
    }
    
}
