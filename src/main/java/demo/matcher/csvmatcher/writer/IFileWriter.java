package demo.matcher.csvmatcher.writer;

import java.io.IOException;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import demo.matcher.csvmatcher.matcher.Match;

public interface IFileWriter {
    public void write(String path, List<Match> matches)
            throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException;
}
