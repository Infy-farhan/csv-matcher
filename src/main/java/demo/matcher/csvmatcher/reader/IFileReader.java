package demo.matcher.csvmatcher.file;

import java.io.FileNotFoundException;
import java.util.List;
import demo.matcher.csvmatcher.model.Transaction;

public interface IFileReader {
    public List<Transaction> read(String path) throws FileNotFoundException;
}
