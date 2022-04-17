package com.mfk.demo.matcher.matcher;

import com.mfk.demo.matcher.input.InputData;
import com.mfk.demo.matcher.model.Threshold;
import com.mfk.demo.matcher.model.Transaction;

import java.util.List;

/**
 * Contract to defined matching algorithm to match rows from
 * buyer csv to rows in seller csv.
 */
public interface IMatcher {

    /**
     * Find matches of {@link Transaction} from list of buyer
     * to list of seller.
     *
     * Returns {@link Match} which contains details on which
     * transaction  from buyer matched to which transaction
     * in seller and type of match.
     *
     * @param inputData
     * @param buyers
     * @param supplier
     * @return
     */
    List<Match> match(InputData inputData, List<Transaction> buyers, List<Transaction> supplier);
}
