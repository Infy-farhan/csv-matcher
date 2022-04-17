package com.mfk.demo.matcher.model;

import lombok.Getter;

@Getter
/**
 * Class to hold threshold value for each data type.
 *
 * It stores separate threshold for string, date and
 * number data type.
 */
public class Threshold {

    private final long stringThreshold;
    private final long dateThreshold;
    private final double numberThreshold;

    public Threshold() {
        this.stringThreshold = 0;
        this.dateThreshold = 0;
        this.numberThreshold = 0.0;
    }

    public Threshold(long stringThreshold, long dateThreshold, double numberThreshold) {
        this.stringThreshold = stringThreshold;
        this.dateThreshold = dateThreshold;
        this.numberThreshold = numberThreshold;
    }
}
