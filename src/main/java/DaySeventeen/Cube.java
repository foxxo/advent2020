package DaySeventeen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cube {
    int x,y, z, w;
    char content;
    Map myMap;

    public Cube(int pX, int pY, int pZ, int pW, char pContent, Map pMap)
    {
        x = pX;
        y = pY;
        z = pZ;
        w = pW;
        content = pContent;
        myMap = pMap;
    }

    public List<Cube> getNeighbors()
    {
        List<Cube> neighbors = new ArrayList();

        for(int i = -1; i < 2; i++)
            for(int j = -1; j < 2; j++)
            {
                for(int k = -1; k < 2; k++)
                    for(int l = -1; l < 2; l++){
                    if (j == 0 && i == 0  && k ==0  && l == 0||
                            x + j < 0 || x + j > myMap.width - 1 ||
                            y + i < 0 || y + i > myMap.height - 1 ||
                            z + k < 0 || z + k > myMap.depth - 1 ||
                            w + l < 0 || w + l > myMap.chrono -1 ||
                            neighbors.contains(myMap.get(x + j, y + i, z+k, w+l)))
                        continue;
                    neighbors.add(myMap.get(x + j, y + i, z + k, w + l));
                }
            }
        return neighbors;
    }


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

}
