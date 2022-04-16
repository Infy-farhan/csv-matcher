package demo.matcher.csvmatcher.matcher;

import demo.matcher.csvmatcher.model.Transaction;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
/**
 * Represnts the result of the matching.
 * The type of match: EXACT, PARTIAL, NO_MATCH is represented by matchType
 * Both EXACT and PARTIAL match has buyes and supplier, NO_MATCH has either buyer or supplier.
 */
public class Match {
    private Transaction buyer;
    private Transaction supplier;
    private MatchType matchType;
}
