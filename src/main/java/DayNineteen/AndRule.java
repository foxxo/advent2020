package DayNineteen;

public class AndRule extends Rule{
    public AndRule() {
        super();
    }

    public boolean eval(String input)
    {
        for(Rule r : subRules)
        {
            if(!r.eval(input))
                return false;
        }
        return true;
    }
}
