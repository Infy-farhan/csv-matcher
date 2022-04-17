package com.mfk.demo.matcher.data.reader;

import com.mfk.demo.matcher.model.Transaction;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * Provides utility methods to read and writes CSV files to and from java beans
 * (Transaction objects)
 */
@Component
public class CsvFileReader implements IFileReader {

    Logger logger = LoggerFactory.getLogger(CsvFileReader.class);

    /**
     * Reads the contents of a CSV file, converts each row into a Transaction object
     * and returns the list of Transaction objects
     *
     * @param path path to the CSV file
     * @return list of Transaction objects representing each row of the file
     */
    public List<Transaction> read(String path) throws FileNotFoundException {
        try {
            List<Transaction> transactions = new CsvToBeanBuilder<Transaction>(new FileReader(path))
                    .withSeparator(',')
                    .withType(Transaction.class)
                    .build()
                    .parse();
            return transactions;
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

}
