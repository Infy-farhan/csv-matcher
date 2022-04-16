package demo.matcher.csvmatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import demo.matcher.csvmatcher.file.CsvFileUtil;
import demo.matcher.csvmatcher.model.Transaction;

@SpringBootTest
public class CsvReaderTest {
	
	@Test
	public void testReadCsvForSuppliers() throws FileNotFoundException {
		String csvPath = "D:\\mdfarhan.khan\\workspace\\SE2 ID360 Craft\\SE2 Craft\\Supplier.csv";
		List<Transaction> list = CsvFileUtil.readCsv(csvPath);
		assertEquals(list.size(), 6);
	}
	
	@Test
	public void testReadCsvForBuyers() throws FileNotFoundException {
		String csvPath = "D:\\mdfarhan.khan\\workspace\\SE2 ID360 Craft\\SE2 Craft\\Buyer.csv";
		List<Transaction> list = CsvFileUtil.readCsv(csvPath);
		assertEquals(list.size(), 6);
	}
	
}
