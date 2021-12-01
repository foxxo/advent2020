package Twenty.DayFour.Data;

public class ExpYear extends Field {
    public ExpYear(String val)
    {
        super(val);
    }

    public boolean isValid()
    {
        return value.length() == 4 && Integer.parseInt(value) >= 2020 && Integer.parseInt(value) <= 2030;
    }
}
