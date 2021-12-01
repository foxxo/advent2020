package Twenty.DayEleven;

import java.util.List;

public class Map {

    Seat grid[][];
    int width;
    int height;

    public Map(List<String> input)
    {
        parseInput(input);
    }

    public Map(int w, int h)
    {
        width = w; height = h; grid = new Seat[width][height];
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                grid[x][y] = new Seat(x, y, '-', this);
            }
        }
    }

    void parseInput(List<String> input)
    {
        width = input.get(0).length();
        height = input.size();

        grid = new Seat[width][height];

        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                grid[x][y] = new Seat(x, y, input.get(y).charAt(x), this);
            }
        }
    }

    public Seat get(int x, int y)
    {
        return grid[x][y];
    }

    public void print()
    {
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                System.out.print(grid[x][y].content);
            }
            System.out.println();
        }
    }

    public Map copy()
    {
        Map newMap = new Map(width, height);

        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++)
            {
                newMap.grid[x][y].content = grid[x][y].content;
            }

        return newMap;
    }

    public boolean isSame(Map otherMap)
    {
        if(width != otherMap.width || height != otherMap.height)
            return false;

        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++)
            {
                if(otherMap.grid[x][y].content != grid[x][y].content)
                    return false;
            }

        return true;
    }

    public int countFilledSeats()
    {
        int count = 0;

        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++)
            {
                if(grid[x][y].content == '#')
                    count++;
            }
        return count;
    }

}
