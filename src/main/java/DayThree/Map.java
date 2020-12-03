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

        for(int j = 0; j < height; j++)
        {
            for(int i = 0; i < width; i++)
            {
                if(input.get(j).charAt(i)=='.')
                    grid[i][j] = new Plot(i, j, false);
                else if(input.get(j).charAt(i)=='#')
                    grid[i][j] = new Plot(i, j, true);
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
        for(int j = 0; j < height; j++)
        {
            for(int i = 0; i < width; i++)
            {
                if(grid[i][j].hasTree)
                    System.out.print("#");
                else
                    System.out.print(".");
            }
            System.out.println();
        }
    }

}
