package DaySeventeen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cube {
    int x,y, z;
    char content;
    Map myMap;

    public Cube(int pX, int pY, int pZ, char pContent, Map pMap)
    {
        x = pX;
        y = pY;
        z = pZ;
        content = pContent;
        myMap = pMap;
    }

    public List<Cube> getNeighbors()
    {
        List<Cube> neighbors = new ArrayList();

        for(int i = -1; i < 2; i++)
            for(int j = -1; j < 2; j++)
            {
                for(int k = -1; k < 2; k++) {
                    if (j == 0 && i == 0  && k ==0 ||
                            x + j < 0 || x + j > myMap.width - 1 ||
                            y + i < 0 || y + i > myMap.height - 1 ||
                            z + k < 0 || z + k > myMap.depth - 1 ||
                            neighbors.contains(myMap.get(x + j, y + i, z+k)))
                        continue;
                    neighbors.add(myMap.get(x + j, y + i, z + k));
                }
            }
        return neighbors;
    }

//    public List<Cube> getVisibleSeats()
//    {
//        List<Cube> visible = new ArrayList();
//
//        for(int i = -1; i < 2; i++)
//            for(int j = -1; j < 2; j++)
//            {
//                if(j == 0 && i == 0 ||
//                        x + j < 0 || x + j > myMap.width-1 ||
//                        y + i < 0 || y + i > myMap.height -1 ||
//                        visible.contains(myMap.get(x + j, y + i)))
//                    continue;
//                Cube visSeat = findFirstSeatInDirection(j, i);
//                if(visSeat != null)
//                    visible.add(visSeat);
//            }
//        return visible;
//    }


    public char runRules()
    {
        List<Cube> neighbors = getNeighbors();

        neighbors = neighbors.stream().filter( n -> n.content == '#').collect(Collectors.toList());


        if(content == '#')
            if (!(neighbors.size() == 2 || neighbors.size() == 3))
                return '.';
        if(content == '.' && neighbors.size() == 3)
            return '#';

        return content;
    }

//    Cube findFirstSeatInDirection(int dX, int dY)
//    {
//        if(x + dX < 0 || x + dX >= myMap.width ||
//                y + dY < 0 || y + dY >= myMap.height)
//            return null;
//        Cube nextSeat = myMap.grid[x+dX][y+dY];
//
//        if(nextSeat.content == 'L' || nextSeat.content == '#')
//            return nextSeat;
//        return nextSeat.findFirstSeatInDirection(dX, dY);
//    }
}
