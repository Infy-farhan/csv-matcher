package com.mfk.demo.matcher.input;

import com.mfk.demo.matcher.model.Threshold;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Class to read input data from command line. This provides
 * feature to let use interact in command line prompt to pass
 * input data to application.
 */
@Component
public class CommandLineInputHandler implements IInputHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineInputHandler.class);

    @Override
    public InputData getInput() {
        Scanner in = new Scanner(System.in);
        try {
            System.out.print("Enter buyer.csv path: ");
            String buyerPath = in.nextLine();
            System.out.print("Enter supplier.csv path: ");
            String supplierPath = in.nextLine();
            System.out.print("Enter output result path: ");
            String resultPath = in.nextLine();
            System.out.println("Enter no of columns: ");
            int noOfColumns = Integer.parseInt(in.nextLine());

            Float numberThreshold;
            Integer stringThreshold, dateThreshold;

            System.out.println("Enter number threshold: ");
            numberThreshold = Float.parseFloat(in.nextLine());
            System.out.println("Enter date threshold (in days): ");
            dateThreshold = Integer.parseInt(in.nextLine());
            System.out.println("Enter string threshold (in number of insertions required to reach other string): ");
            stringThreshold = Integer.parseInt(in.nextLine());

            Threshold threshold = new Threshold(stringThreshold,dateThreshold,numberThreshold);
            return new InputData(buyerPath, supplierPath, resultPath, noOfColumns, threshold);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        } finally {
            in.close();
        }
    }

}
