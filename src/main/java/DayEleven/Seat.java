package DayEleven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Seat {
    int x,y;
    char content;
    Map myMap;

    public Seat(int pX, int pY, char pContent, Map pMap)
    {
        x = pX;
        y = pY;
        content = pContent;
        myMap = pMap;
    }

    public List<Seat> getNeighbors()
    {
        List<Seat> neighbors = new ArrayList();

        for(int i = -1; i < 2; i++)
            for(int j = -1; j < 2; j++)
            {
                if(j == 0 && i == 0 ||
                x + j < 0 || x + j > myMap.width-1 ||
                y + i < 0 || y + i > myMap.height -1 ||
                neighbors.contains(myMap.get(x + j, y + i)))
                    continue;
                neighbors.add(myMap.get(x + j, y + i));
            }
        return neighbors;
    }

    public List<Seat> getVisibleSeats()
    {
        List<Seat> visible = new ArrayList();

        for(int i = -1; i < 2; i++)
            for(int j = -1; j < 2; j++)
            {
                if(j == 0 && i == 0 ||
                        x + j < 0 || x + j > myMap.width-1 ||
                        y + i < 0 || y + i > myMap.height -1 ||
                        visible.contains(myMap.get(x + j, y + i)))
                    continue;
                Seat visSeat = findFirstSeatInDirection(j, i);
                if(visSeat != null)
                    visible.add(visSeat);
            }
        return visible;
    }


    public char runRules()
    {
        if(content == '.')
            return '.';


        List<Seat> neighbors = getVisibleSeats();
        neighbors = neighbors.stream().filter( n -> n.content == '#').collect(Collectors.toList());


        if(content == 'L' && neighbors.size() == 0)
            return '#';
        if(content == '#' && neighbors.size() >= 5)
            return 'L';

        return content;
    }

    Seat findFirstSeatInDirection(int dX, int dY)
    {
        if(x + dX < 0 || x + dX >= myMap.width ||
                y + dY < 0 || y + dY >= myMap.height)
            return null;
        Seat nextSeat = myMap.grid[x+dX][y+dY];

        if(nextSeat.content == 'L' || nextSeat.content == '#')
            return nextSeat;
        return nextSeat.findFirstSeatInDirection(dX, dY);
    }
}
