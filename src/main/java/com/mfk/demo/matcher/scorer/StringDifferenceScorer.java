package com.mfk.demo.matcher.scorer;

import com.mfk.demo.matcher.constant.Constant;
import com.mfk.demo.matcher.input.InputData;
import org.springframework.stereotype.Component;

/**
 * Class provides concrete algorithm for calculating difference
 * score of two String objects.
 */
@Component(Constant.STRING_SCORER)
public class StringDifferenceScorer implements IDifferenceScorer<String> {

    @Override
    public Double getScore(InputData inputData, String source, String destination) {
        int diff = editDistance(source, destination);
        return diff > inputData.getThreshold().getStringThreshold() ? inputData.getNoOfColumns() + 1.0 : (double) diff / inputData.getThreshold().getStringThreshold();
    }

    private int editDistance(String source, String destination) {
        // Create a table to store results of sub-problems
        int sourceLength = source.length();
        int destinationLength = destination.length();
        int editDistance[][] = new int[sourceLength + 1][destinationLength + 1];

        // Fill editDistance[][] in bottom up manner
        for (int sourceIndex = 0; sourceIndex <= sourceLength; sourceIndex++) {
            for (int destinationIndex = 0; destinationIndex <= destinationLength; destinationIndex++) {
                // If first string is empty, only option is
                // to insert all characters of second string
                if (sourceIndex == 0) {
                    editDistance[sourceIndex][destinationIndex] = destinationIndex; // Min. operations = destinationIndex
                }
                // If second string is empty, only option is
                // to remove all characters of second string
                else if (destinationIndex == 0) {
                    editDistance[sourceIndex][destinationIndex] = sourceIndex; // Min. operations = sourceIndex
                }
                // If last characters are same, ignore last
                // char and recur for remaining string
                else if (source.charAt(sourceIndex - 1) == destination.charAt(destinationIndex - 1))
                    editDistance[sourceIndex][destinationIndex] = editDistance[sourceIndex - 1][destinationIndex - 1];

                    // If the last character is different,
                    // consider all possibilities and find the
                    // minimum
                else
                    editDistance[sourceIndex][destinationIndex] = 1
                            + min(editDistance[sourceIndex][destinationIndex - 1], // Insert
                            editDistance[sourceIndex - 1][destinationIndex], // Remove
                            editDistance[sourceIndex - 1][destinationIndex - 1]); // Replace
            }
        }
        return editDistance[sourceLength][destinationLength];
    }

    private int min(int first, int second, int third) {
        if (first <= second && first <= third) {
            return first;
        }
        if (second <= first && second <= third) {
            return second;
        }
        return third;
    }

}
