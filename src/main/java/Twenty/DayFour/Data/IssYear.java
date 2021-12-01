package Twenty.DayFour.Data;

public class IssYear extends Field {
    public IssYear(String val)
    {
        super(val);
    }

    public boolean isValid()
    {
        return value.length() == 4 && Integer.parseInt(value) >= 2010 && Integer.parseInt(value) <= 2020;
    }
}
