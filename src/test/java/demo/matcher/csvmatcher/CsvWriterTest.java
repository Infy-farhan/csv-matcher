package demo.matcher.csvmatcher;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import org.junit.jupiter.api.Test;

import demo.matcher.csvmatcher.io.CsvFileReader;
import demo.matcher.csvmatcher.matcher.Match;
import demo.matcher.csvmatcher.matcher.MatchType;
import demo.matcher.csvmatcher.model.Transaction;

public class CsvWriterTest {
    // @Test
    // public void writerTest() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
    //     Transaction t1 = getT1();
    //     Transaction t2 = getT1();
    //     Match match = new Match();
    //     match.setBuyer(t1);
    //     match.setSupplier(t2);
    //     match.setMatchType(MatchType.EXACT);
    //     List<Match> list = new ArrayList<>();
    //     list.add(match);
    //     CsvFileReader.writeCsv("D:\\mdfarhan.khan\\workspace\\SE2 ID360 Craft\\SE2 Craft\\TestResults.csv", list);
    // }

    // @Test
    // public void writerTestString() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
    //     Transaction t1 = getT1();
    //     Transaction t2 = getT1();
    //     Match match = new Match();
    //     match.setBuyer(t1);
    //     match.setSupplier(t2);
    //     match.setMatchType(MatchType.EXACT);
    //     List<Match> list = new ArrayList<>();
    //     list.add(match);
    //     String res = CsvFileUtil.writeCsvToString(list);
    //     System.out.println("Result: " + res);
    // }

    // @Test
    // void writerTestCustom() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
    //     ColumnPositionMappingStrategy<Person> mappingStrategy = new ColumnPositionMappingStrategy<>();
    //     mappingStrategy.setType(Person.class);
    //     mappingStrategy.setColumnMapping(new String [] { "name", "age", "address", "office" });
    //     Writer writer = new StringWriter();
    //     StatefulBeanToCsv<Person> beanWriter = new StatefulBeanToCsvBuilder<Person>(writer)
    //             .withMappingStrategy(mappingStrategy)
    //             .withSeparator(',')
    //             .build();
    //     Person p = new Person();
    //     p.setName("Mr X");
    //     p.setAge(13);
    //     Address home = new Address();
    //     home.setAddress("my address");
    //     p.setHome(home);
    //     Address office = new Address();
    //     office.setAddress("my office");
    //     p.setOffice(office);
    //     List<Person> list = new ArrayList<>();
    //     list.add(p);
    //     beanWriter.write(list);
    //     System.out.println("Result: " + writer.toString());
    // }

    private Transaction getT1() {
        Transaction t1 = new Transaction();
        t1.setGstin("29AAACB2894G1ZJ");
        t1.setDate(LocalDate.of(2017, 9, 3));
        t1.setBillNo("474330129");
        t1.setGstRate(18f);
        t1.setTaxableValue(2099.0f);
        t1.setCgst(188.91f);
        t1.setSgst(188.91f);
        t1.setTotal(2476.82f);
        return t1;
    }

}


