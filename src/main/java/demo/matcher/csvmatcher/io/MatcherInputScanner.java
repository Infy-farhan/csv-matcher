package demo.matcher.csvmatcher.io;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class MatcherInputScanner implements IInputScanner{
    Scanner in = new Scanner(System.in);
    @Override
    public InputData getInputs() {
		System.out.print("Enter buyer.csv path: ");
		String buyerPath = in.nextLine();
		System.out.print("Enter supplier.csv path: ");
		String supplierPath = in.nextLine();
		System.out.print("Enter output result path: ");
		String resultPath = in.nextLine();
        System.out.println("Enter number threshold: ");
        Float numberThreshold = Float.parseFloat(in.nextLine());
        System.out.println("Enter date threshold (in days): ");
        Integer dateThreshold = Integer.parseInt(in.nextLine());
        System.out.println("Enter string threshold (in number of insertions required to reach other string): ");
        Integer stringThreshold = Integer.parseInt(in.nextLine());
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
