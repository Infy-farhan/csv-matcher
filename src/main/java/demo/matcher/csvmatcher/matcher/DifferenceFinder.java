package demo.matcher.csvmatcher.matcher;

import demo.matcher.csvmatcher.model.Transaction;

public interface DifferenceFinder {
    public Float getDifferenceScore(Transaction t1, Transaction t2);
}
