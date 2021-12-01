package Twenty.DaySixteen;


import java.util.Arrays;
import java.util.List;

public class Rule {
    String field;
    List<String> validRanges;

    public Rule(String input)
    {
        field = input.substring(0, input.indexOf(": "));
        validRanges = Arrays.asList(input.substring(input.indexOf(": ")+2).split(" or "));
    }

    public boolean checkValue(int v)
    {
        for(String range : validRanges)
        {
            int min = Integer.parseInt(range.substring(0, range.indexOf("-")));
            int max = Integer.parseInt(range.substring(range.indexOf("-")+1));
            if(v >= min && v <= max)
                return true;
        }
        return false;
    }

    public String toString()
    {
        return "Rule for " + field + " field: " + validRanges;
    }
}
