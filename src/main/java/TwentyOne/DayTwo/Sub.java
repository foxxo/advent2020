package TwentyOne.DayTwo;

public class Sub {
    Vector pos;
    int aim;

    public Sub()
    {
        pos = new Vector(0,0,0);
        aim = 0;
    }

    public void move(Sub s)
    {
        pos = pos.add(s.pos);
        aim += s.aim;
    }
}
