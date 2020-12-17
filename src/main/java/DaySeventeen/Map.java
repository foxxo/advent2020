package DaySeventeen;

import java.util.List;

public class Map {

    Cube grid[][][];
    int width;
    int height;
    int depth;

    public Map(List<String> input)
    {
        parseInput(input);
    }

    public Map(int w, int h, int d)
    {
        width = w; height = h; depth = d;
        grid = new Cube[width][height][depth];
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                for(int z = 0; z < depth; z++)
                    grid[x][y][z] = new Cube(x, y, z,'.', this);
            }
        }
    }

    void parseInput(List<String> input)
    {
        width = input.get(0).length();
        height = input.size();
        depth = 1;

        grid = new Cube[width][height][depth];

        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                for(int z = 0; z < depth; z++)
                    grid[x][y][z] = new Cube(x, y, z, input.get(y).charAt(x), this);
            }
        }
    }

    public Cube get(int x, int y, int z)
    {
        return grid[x][y][z];
    }

    public void print()
    {
        System.out.println("-------------------------------------------------------------------------------------");
        for(int z = 0; z < depth; z++) {
            System.out.println("Z-level: " + (z - depth / 2));
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    System.out.print(grid[x][y][z].content);
                }
                System.out.println();
            }
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public Map copy()
    {
        Map newMap = new Map(width, height, depth);

        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++)
            {
                for(int z = 0; z < depth; z++)
                    newMap.grid[x][y][z].content = grid[x][y][z].content;
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
                for(int z = 0; z < depth; z++)
                    if(otherMap.grid[x][y][z].content != grid[x][y][z].content)
                        return false;
            }

        return true;
    }

    public int countActiveCubes()
    {
        int count = 0;

        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++)
            {
                for(int z = 0; z < depth; z++)
                    if(grid[x][y][z].content == '#')
                        count++;
            }
        return count;
    }

    public Map expandBoundaries()
    {
        Map expandedMap = new Map(width+2, height+2, depth+2);
        expandedMap.merge(this);
        return expandedMap;

    }

    private void merge(Map smallerMap)
    {
        for(int y = 0; y < smallerMap.height; y++)
            for(int x = 0; x < smallerMap.width; x++)
            {
                for(int z = 0; z < smallerMap.depth; z++)
                   grid[x+1][y+1][z+1].content = smallerMap.get(x, y, z).content;
            }
    }


}
