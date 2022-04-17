package com.mfk.demo.matcher.matcher;

import com.mfk.demo.matcher.input.InputData;
import com.mfk.demo.matcher.model.Threshold;
import com.mfk.demo.matcher.model.Transaction;

import java.util.List;

public interface IMatcher {
    /**
     * Returns a list of Match objects represnting the matching result of the lists of buyes and suppliers
     *
     * @param buyers   list of Transactions in buyers csv
     * @param supplier list of Trasactions in suppliers csv
     * @return result of the comparision in for of a list of Match ojects
     */
    List<Match> match(InputData inputData, List<Transaction> buyers, List<Transaction> supplier);
}
