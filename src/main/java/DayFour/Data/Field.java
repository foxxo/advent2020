package DayFour.Data;

import DayFour.DayFour;

public abstract class Field {
    String value;

    public void setValue(String val)
    {
        value = val;
    }

    public String getValue()
    {
        return value;
    }

    public Field(String val)
    {
        value = val;
    }

    public abstract boolean isValid();

    public String toString()
    {
        return value;
    }
}
