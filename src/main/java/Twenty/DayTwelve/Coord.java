package Twenty.DayTwelve;

public class Coord {
    int x, y;

    public Coord(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void add(Coord c)
    {
        x += c.x;
        y += c.y;
    }

    public Coord times(int mult)
    {
        return new Coord(x * mult, y * mult);
    }

    public String toString()
    {
        return ("(" +  x + ", " + y + ")");
    }
}
