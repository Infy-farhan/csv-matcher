package demo.matcher.csvmatcher.matcher;

import demo.matcher.csvmatcher.model.Transaction;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Match {
    private Transaction buyer;
    private Transaction supplier;
    private MatchType matchType;
}
