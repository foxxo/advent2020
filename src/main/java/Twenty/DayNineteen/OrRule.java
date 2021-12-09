package Twenty.DayNineteen;

public class OrRule extends Rule {
    public OrRule() {
        super();
    }

    public boolean eval(String input)
    {
        for(Rule r : subRules)
        {
            if(r.eval(input))
                return true;
        }
        return false;
    }
}
