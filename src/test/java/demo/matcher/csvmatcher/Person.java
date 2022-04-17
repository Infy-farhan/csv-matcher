package demo.matcher.csvmatcher;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Person {
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "age")
    private int age;
    @CsvRecurse
    private Address home;
    @CsvRecurse
    private Address office;
}
