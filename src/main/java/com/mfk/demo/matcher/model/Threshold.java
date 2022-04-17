package com.mfk.demo.matcher.model;

import lombok.Getter;

@Getter
public class Threshold {

    private long stringThreshold;
    private long dateThreshold;
    private double numberThreshold;

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
