package demo.matcher.csvmatcher.matcher;

import java.util.List;

import demo.matcher.csvmatcher.model.Transaction;

public interface Matcher {
    /**
     * Returns a list of Match objects represnting the matching result of the lists of buyes and suppliers
     * @param buyers list of Transactions in buyers csv
     * @param supplier list of Trasactions in suppliers csv
     * @return result of the comparision in for of a list of Match ojects
     */
    public abstract List<Match> compare(List<Transaction> buyers, List<Transaction> supplier);
}
