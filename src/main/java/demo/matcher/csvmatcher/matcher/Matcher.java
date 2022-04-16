package demo.matcher.csvmatcher.matcher;

import java.util.List;

import demo.matcher.csvmatcher.model.Transaction;

public interface Matcher {
    public abstract List<Match> compare(List<Transaction> buyers, List<Transaction> supplier);
}
