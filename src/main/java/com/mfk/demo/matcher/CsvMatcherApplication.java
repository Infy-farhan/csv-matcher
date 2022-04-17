package com.mfk.demo.matcher;

import com.mfk.demo.matcher.data.reader.IFileReader;
import com.mfk.demo.matcher.data.writer.IFileWriter;
import com.mfk.demo.matcher.matcher.IMatcher;
import com.mfk.demo.matcher.matcher.Match;
import com.mfk.demo.matcher.model.Transaction;
import com.mfk.demo.matcher.input.IInputHandler;
import com.mfk.demo.matcher.input.InputData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CsvMatcherApplication implements CommandLineRunner {

    private final IInputHandler inputScanner;
    private final IFileReader fileReader;
    private final IFileWriter fileWriter;
    private final IMatcher matcher;

    public CsvMatcherApplication(IInputHandler inputScanner, IFileReader fileReader, IFileWriter fileWriter, IMatcher matcher) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
        this.inputScanner = inputScanner;
        this.matcher = matcher;
    }

    public static void main(String[] args) {
        SpringApplication.run(CsvMatcherApplication.class, args);
    }

    /**
     * This method starts the execution of the matcher application.
     * It takes input form the user and creates the matcher object based on the inputs.
     * Then runs the matching algorithm and writes the result in an output file.
     */
    @Override
    public void run(String... args) throws Exception {

        InputData inputData = inputScanner.getInput();

        List<Transaction> buyers = fileReader.read(inputData.getBuyerPath());
        List<Transaction> suppliers = fileReader.read(inputData.getSupplierPath());

        List<Match> matchList = matcher.match(inputData.getThreshold(),buyers, suppliers);

        fileWriter.write(inputData.getResultPath(), matchList);
    }

}
