package demo.matcher.csvmatcher.similaritycalculator;

import java.util.function.BiFunction;

/**
 * Simple String Similarity Calculator based on subsequence check
 */
public class StringSimilarityCalculator implements BiFunction<String, String, Float>{
    int threshold;
    public StringSimilarityCalculator(int threshold){
        this.threshold = threshold;
    }
    /**
     * Return the difference score of two String
     * The difference score is the number of insertions required to reach one string from another
     * If one string in not the sequence of another, it returns -1 meaning they do not match.
     * @param str1
     * @param str2
     * @return difference score of the two strings 
     */
    @Override
    public Float apply(String str1, String str2) {
        if(str1==null) str1 = "";
        if(str2==null) str2 = "";
        String small, large;
        if(str1.length()<str2.length()){
            small = str1;
            large = str2;
        }else{
            small = str2;
            large = str1;
        }
        int j = 0;
        for (int i = 0; i < large.length() && j < small.length(); i++){
            if (small.charAt(j) == large.charAt(i)) j++;
        }
        if(j==small.length()){
            float diff = large.length() - j;
            return diff>threshold? -1f: diff;
        }
        return -1f;
    }
    
}
