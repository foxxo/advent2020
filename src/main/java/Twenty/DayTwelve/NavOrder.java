package Twenty.DayTwelve;

public class NavOrder {
    int value;
    char command;

    public NavOrder(String s)
    {
        command = s.charAt(0);
        value = Integer.parseInt(s.substring(1));
    }
}
