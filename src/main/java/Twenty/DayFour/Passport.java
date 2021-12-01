package Twenty.DayFour;

import Twenty.DayFour.Data.*;

import java.util.HashMap;
import java.util.List;

public class Passport extends HashMap<String, Field> {


    public Passport(String input)
    {
        processString(input);
    }

    public boolean isValid(List<String> requiredFields)
    {
        for(String s : requiredFields)
        {
            if(this.get(s) == null)
                return false;
            if(!this.get(s).isValid())
                return false;
        }
        return true;
    }

    private void processString(String input)
    {
        for(String s : input.split(" "))
        {
            this.put(s.substring(0, s.indexOf(":")), createField(s.substring(0, s.indexOf(":")), s.substring(s.indexOf(":")+1)));
        }
    }

    Field createField(String name, String value)
    {
        switch(name) {
            case "byr":
                return new BirthYear(value);
            case "eyr":
                return new ExpYear(value);
            case "iyr":
                return new IssYear(value);
            case "hgt":
                return new Height(value);
            case "ecl":
                return new EyeColor(value);
            case "hcl":
                return new HairColor(value);
            case "pid":
                return new PID(value);
        }
        return null;
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder();
        for(Field f : this.values())
        {
            if(null != f) {
                s.append(f.getClass().toString())
                        .append(": ")
                        .append(f.toString())
                        .append("\n");
            }
        }
        return s.toString();
    }
}
