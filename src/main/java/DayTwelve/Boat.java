package DayTwelve;

public class Boat {
    int heading;
    Coord position;
    Coord direction;

    static final Coord EAST = new Coord(1, 0);
    static final Coord WEST = new Coord(-1, 0);
    static final Coord NORTH = new Coord(0, 1);
    static final Coord SOUTH = new Coord(0, -1);

    public Boat(int posX, int posY, int h)
    {
        position = new Coord(posX, posY);
        heading = h;
        direction = new Coord(1, 0);
    }

    void processOrder(NavOrder o)
    {
        switch(o.command)
        {
            case 'F':
                position.add(direction.times(o.value));
                break;
            case 'E':
                position.add(EAST.times(o.value));
                break;
            case 'N':
                position.add(NORTH.times(o.value));
                break;
            case 'W':
                position.add(WEST.times(o.value));
                break;
            case 'S':
                position.add(SOUTH.times(o.value));
                break;
            case 'R':
                heading += o.value;
                if(heading >= 360)
                    heading -= 360;
                updateDirection();
                break;
            case 'L':
                heading -= o.value;
                if(heading < 0)
                    heading += 360;
                updateDirection();
                break;
        }
    }

    void updateDirection()
    {
        double radians = Math.toRadians(heading);
        direction.x = (int)Math.sin(radians);
        direction.y = (int)Math.cos(radians);;
    }

}
