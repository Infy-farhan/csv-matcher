package demo.matcher.csvmatcher.similaritycalculator;

import java.util.function.BiFunction;

public class NumberSimilarityCalculator implements BiFunction<Float, Float, Float>{
    private Float threshold;

    public NumberSimilarityCalculator(Float threshold){
        this.threshold = threshold;
    }

    @Override
    public Float apply(Float t, Float u) {
        if(t==null) t = 0f;
        if(u==null) u = 0f;
        Float difference = Math.abs(t-u);
        if(difference>threshold) return -1f;
        return difference;
    }
}
