package Twenty.DayFour.Data;

public class HairColor extends Field{

    public HairColor(String val)
    {
        super(val);
    }
    @Override
    public boolean isValid() {
        if(value.length() == 7 && value.charAt(0) == '#')
        {
            for(Character c : value.substring(1).toCharArray())
            {
                if(!Character.isLetterOrDigit(c))
                    return false;
            }
            return true;
        }
        return false;
    }
}
