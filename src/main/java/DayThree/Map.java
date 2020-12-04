package DayThree;

import java.util.List;

public class Map {

    Plot grid[][];
    int width;
    int height;

    public Map(List<String> input)
    {
        parseInput(input);
    }

    void parseInput(List<String> input)
    {
        width = input.get(0).length();
        height = input.size();

        grid = new Plot[width][height];

        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                if(input.get(y).charAt(x)=='.')
                    grid[x][y] = new Plot(x, y, false);
                else if(input.get(y).charAt(x)=='#')
                    grid[x][y] = new Plot(x, y, true);
            }
        }
    }

    public Plot get(int x, int y)
    {
        return grid[x][y];
    }

    public Plot move(Plot origin, int dX, int dY)
    {
        int destX = origin.x + dX;
        int destY = origin.y + dY;

        if(destX > width-1)
            destX -= width;
        else if(destX < 0)
            destX += width;

        return(grid[destX][destY]);
    }

    public int collisionsForPattern(int dX, int dY)
    {
        Plot position = get(0,0);
        int trees = 0;

        while(position.y < height-1)
        {
            position = move(position, dX, dY);
            trees += (position.hasTree ? 1 : 0);
        }
        return trees;
    }

    public void print()
    {
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                if(grid[x][y].hasTree)
                    System.out.print("#");
                else
                    System.out.print(".");
            }
            System.out.println();
        }
    }

}
