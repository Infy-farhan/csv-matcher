package demo.matcher.csvmatcher.similaritycalculator;

import java.util.function.BiFunction;

/**
 * Simple Number Similarity Calculator based on the absolute difference
 */
public class NumberSimilarityCalculator implements BiFunction<Float, Float, Float>{
    private Float threshold;

    public NumberSimilarityCalculator(Float threshold){
        this.threshold = threshold;
    }

    /**
     * Returns the differnce score of two floats.
     * The difference score is the absolute difference between the two floats
     * @param t 
     * @param u
     * @return The difference score of the two floats
     */
    @Override
    public Float apply(Float t, Float u) {
        if(t==null) t = 0f;
        if(u==null) u = 0f;
        Float difference = Math.abs(t-u);
        if(difference>threshold) return -1f;
        return difference;
    }
}
