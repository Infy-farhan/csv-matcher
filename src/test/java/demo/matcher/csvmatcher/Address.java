package demo.matcher.csvmatcher;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    @CsvBindByName( column = "address")
    private String address;
}
