package demo.matcher.csvmatcher.scanner;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CommandLineInputScanner implements IInputScanner {
    Logger logger = LoggerFactory.getLogger(CommandLineInputScanner.class);

    @Override
    public InputData getInputs() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter buyer.csv path: ");
        String buyerPath = in.nextLine();
        System.out.print("Enter supplier.csv path: ");
        String supplierPath = in.nextLine();
        System.out.print("Enter output result path: ");
        String resultPath = in.nextLine();
        Float numberThreshold;
        Integer stringThreshold, dateThreshold;
        try {
            System.out.println("Enter number threshold: ");
            numberThreshold = Float.parseFloat(in.nextLine());
            System.out.println("Enter date threshold (in days): ");
            dateThreshold = Integer.parseInt(in.nextLine());
            System.out.println("Enter string threshold (in number of insertions required to reach other string): ");
            stringThreshold = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }finally{
            in.close();
        }
        InputData input = new InputData();
        input.setBuyerPath(buyerPath);
        input.setSupplierPath(supplierPath);
        input.setResultPath(resultPath);
        input.setNumberThreshold(numberThreshold);
        input.setStringThreshold(stringThreshold);
        input.setDateThreshold(dateThreshold);
        return input;
    }

}
