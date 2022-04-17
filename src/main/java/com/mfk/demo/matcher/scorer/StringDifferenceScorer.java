package com.mfk.demo.matcher.scorer;

import com.mfk.demo.matcher.model.Threshold;
import org.springframework.stereotype.Component;

/**
 * Simple String Similarity Calculator based on subsequence check
 */
@Component
public class StringDifferenceScorer implements IDifferenceScorer<String> {

    /**
     * Return the difference score of two String
     * The difference score is the number of insertions required to reach one string from another
     * If one string in not the sequence of another, it returns -1 meaning they do not match.
     *
     * @param source
     * @param destination
     * @return difference score of the two strings
     */
    @Override
    public Double getScore(Threshold threshold, String source, String destination) {
       //TODO: Edit distance
        return -1.0;
    }

}
