package com.mfk.demo.matcher.data.reader;

import java.util.List;

import com.mfk.demo.matcher.model.Transaction;

public interface IFileReader {
    public List<Transaction> read(String path) throws Exception;
}
