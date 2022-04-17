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
     * The difference score is the number of insertions required to reach one string
     * from another
     * If one string in not the sequence of another, it returns -1 meaning they do
     * not match.
     *
     * @param source
     * @param destination
     * @return difference score of the two strings
     */
    @Override
    public Double getScore(Threshold threshold, String source, String destination) {
        int diff = editDistance(source, destination);
        return diff>threshold.getStringThreshold()? -1.0 : diff;
    }

    private int editDistance(String source, String destination) {
        // Create a table to store results of subproblems
        int sourceLength = source.length();
        int destinationLength = destination.length();
        int dp[][] = new int[sourceLength + 1][destinationLength + 1];

        // Fill d[][] in bottom up manner
        for (int i = 0; i <= sourceLength; i++) {
            for (int j = 0; j <= destinationLength; j++) {
                // If first string is empty, only option is
                // to insert all characters of second string
                if (i == 0){
                    dp[i][j] = j; // Min. operations = j
                }
                // If second string is empty, only option is
                // to remove all characters of second string
                else if (j == 0){
                    dp[i][j] = i; // Min. operations = i
                }
                // If last characters are same, ignore last
                // char and recur for remaining string
                else if (source.charAt(i - 1) == destination.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];

                // If the last character is different,
                // consider all possibilities and find the
                // minimum
                else
                    dp[i][j] = 1
                            + min(dp[i][j - 1], // Insert
                                    dp[i - 1][j], // Remove
                                    dp[i - 1][j - 1]); // Replace
            }
        }
        return dp[sourceLength][destinationLength];
    }

    private int min(int x, int y, int z) {
        if (x <= y && x <= z){
            return x;
        }
        if (y <= x && y <= z){
            return y;
        }
            return z;
    }

}
