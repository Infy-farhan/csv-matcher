package com.mfk.demo.matcher.data.writer;

import java.io.IOException;
import java.util.List;

import com.mfk.demo.matcher.matcher.Match;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public interface IFileWriter {
    public void write(String path, List<Match> matches)
            throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException;
}
