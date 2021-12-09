package Twenty.DayNineteen;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    List<Rule> subRules;
    String matchValue;

    public Rule()
    {
        subRules = new ArrayList<>();
    }

    public boolean eval(String input)
    {
        if(subRules.size() > 0) {
            return subRules.get(0).eval(input);
        }
        return (input.equals(matchValue));
    }
}
