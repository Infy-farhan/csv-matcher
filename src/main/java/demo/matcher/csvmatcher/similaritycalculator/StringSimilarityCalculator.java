package demo.matcher.csvmatcher.similaritycalculator;

import java.util.function.BiFunction;

/**
 * Simple String Similarity Calculator
 */
public class StringSimilarityCalculator implements BiFunction<String, String, Float>{

    /**
     * Return the difference score of two String
     * The difference score is the number of insertions required to reach one string from another
     * If one string cannot be created from the other string just by insertions, it returns -1 meaning they do not match.
     * The method is based on the fact that the Largest Common Subsequence of two similar strings (as per the definition given earlier)
     * will be same (same lenght) as the smaller string of the two.
     * @param str1
     * @param str2
     * @return difference score of the two strings 
     */
    @Override
    public Float apply(String str1, String str2) {
        if(str1==null) str1 = "";
        if(str2==null) str2 = "";
        int m = str1.length(), n = str2.length();
        int L[][] = new int[m + 1][n + 1];
        int i, j;
        // Following steps build L[m+1][n+1] in
        // bottom up fashion. Note that L[i][j]
        // contains length of LCS of str1[0..i-1]
        // and str2[0..j-1]
        for (i = 0; i <= m; i++) {
            for (j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    L[i][j] = L[i - 1][j - 1] + 1;
                else
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
            }
        }
        // L[m][n] contains length of LCS
        // for X[0..n-1] and Y[0..m-1]
        int minLength = Math.min(m, n);
        int maxLength = Math.max(m, n);
        if(L[m][n]==minLength) return (float) (maxLength - L[m][n]);
        return -1f;
    }
    
}
