package TwentyOne.DayTwo;

public class Vector {
    public int x, y, z;

    public Vector(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector add(Vector v)
    {
        return new Vector(this.x + v.x, this.y + v.y, this.z + v.z);
    }
}
