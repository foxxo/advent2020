package Twenty.DayFour.Data;

public class BirthYear extends Field{

    public BirthYear(String val)
    {
        super(val);
    }

    public boolean isValid()
    {
        return value.length() == 4 && Integer.parseInt(value) >= 1920 && Integer.parseInt(value) <= 2002;
    }
}
