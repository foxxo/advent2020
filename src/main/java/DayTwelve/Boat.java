package DayTwelve;

public class Boat {
    int heading;
    Coord position;
    Coord direction;
    Coord waypoint;

    static final Coord EAST = new Coord(1, 0);
    static final Coord WEST = new Coord(-1, 0);
    static final Coord NORTH = new Coord(0, 1);
    static final Coord SOUTH = new Coord(0, -1);

    public Boat(int posX, int posY, int h)
    {
        position = new Coord(posX, posY);
        heading = h;
        direction = new Coord(1, 0);
        waypoint = new Coord(posX + 10, posY + 1);
    }

    void processAbsoluteOrder(NavOrder o)
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

    public void processRelativeOrder(NavOrder o)
    {
        switch(o.command)
        {
            case 'F':
                position.add(waypoint.times(o.value));
                break;
            case 'E':
                waypoint.add(EAST.times(o.value));
                break;
            case 'N':
                waypoint.add(NORTH.times(o.value));
                break;
            case 'W':
                waypoint.add(WEST.times(o.value));
                break;
            case 'S':
                waypoint.add(SOUTH.times(o.value));
                break;
            case 'R':
                rotateWaypoint(o.value);
                break;
            case 'L':
                rotateWaypoint(360-o.value);
                break;
        }

    }

    void rotateWaypoint(int angle)
    {
        double radians = Math.toRadians(angle);
        int newX = (int) Math.round(waypoint.x*Math.cos(radians) - waypoint.y*Math.sin(-radians));
        int newY = (int) Math.round(waypoint.x*Math.sin(-radians) + waypoint.y*Math.cos(radians));

        waypoint.x = newX;
        waypoint.y = newY;
    }

    void updateDirection()
    {
        double radians = Math.toRadians(heading);
        direction.x = (int)Math.sin(radians);
        direction.y = (int)Math.cos(radians);

    }

}
