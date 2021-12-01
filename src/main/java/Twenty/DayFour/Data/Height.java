package Twenty.DayFour.Data;

public class Height extends Field {

    int height;
    String unit;

    public Height(String val)
    {
        super(val);
        if(value.length() < 3)
        {
            unit = "";
            height = 0;
            return;
        }
        height = Integer.parseInt(value.substring(0, value.length() -2));
        unit = value.substring(value.length() -2);
    }

    public boolean isValid() {

       if(unit.equals("cm"))
           return height >= 150 && height <= 193;
       if(unit.equals("in"))
           return height >= 59 && height <= 76;

        return false;
    }
}
