package DayFour.Data;

import java.util.Arrays;
import java.util.List;

public class EyeColor extends Field{

    public EyeColor(String val)
    {
        super(val);
    }

    private List<String> validColors = Arrays.asList("amb", "blu", "gry", "grn", "hzl", "brn", "oth");

    @Override
    public boolean isValid() {
        return validColors.contains(value);
    }
}
