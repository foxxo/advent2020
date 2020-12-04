package DayFour.Data;

public class PID extends Field {
    public PID(String val)
    {
        super(val);
    }
    @Override
    public boolean isValid() {
        if(value.length() == 9)
        {
            for(Character c : value.toCharArray())
            {
                if(!Character.isDigit(c))
                    return false;
            }
            return true;
        }
        return false;
    }
}
