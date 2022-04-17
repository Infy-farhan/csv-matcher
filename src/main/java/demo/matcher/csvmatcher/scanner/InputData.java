package demo.matcher.csvmatcher.io;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputData {
    private String buyerPath;
    private String supplierPath;
    private String resultPath;
    private Float numberThreshold;
    private Integer dateThreshold;
    private Integer stringThreshold;
}
