package com.mfk.demo.matcher.input;

import com.mfk.demo.matcher.model.Threshold;
import lombok.Getter;

@Getter
public class InputData {

    private final String buyerPath;
    private final String supplierPath;
    private final String resultPath;
    private final Threshold threshold;

    public InputData(String buyerPath, String supplierPath, String resultPath, Threshold threshold) {
        this.buyerPath = buyerPath;
        this.supplierPath = supplierPath;
        this.resultPath = resultPath;
        this.threshold = threshold;
    }
}
