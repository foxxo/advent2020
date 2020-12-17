package DaySeventeen;

import java.util.List;

public class Map {

    Cube grid[][][][];
    int width;
    int height;
    int depth;
    int chrono;

    public Map(List<String> input)
    {
        parseInput(input);
    }

    public Map(int wid, int h, int d, int c)
    {
        width = wid; height = h; depth = d; chrono = c;
        grid = new Cube[width][height][depth][chrono];
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                for(int z = 0; z < depth; z++)
                    for(int w = 0; w < chrono; w++)
                        grid[x][y][z][w] = new Cube(x, y, z, w,'.', this);
            }
        }
    }

    void parseInput(List<String> input)
    {
        width = input.get(0).length();
        height = input.size();
        depth = 1;
        chrono = 1;

        grid = new Cube[width][height][depth][chrono];

        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                for(int z = 0; z < depth; z++)
                    for(int w = 0; w < chrono; w++)
                        grid[x][y][z][w] = new Cube(x, y, z, w, input.get(y).charAt(x), this);
            }
        }
    }

    public Cube get(int x, int y, int z, int w)
    {
        return grid[x][y][z][w];
    }

    public void print()
    {
        System.out.println("-------------------------------------------------------------------------------------");
        for(int z = 0; z < depth; z++) {
            System.out.println("Z-level: " + (z - depth / 2));
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++)
                    for(int w = 0; w < chrono; w++){
                        System.out.print(grid[x][y][z][w].content);
                }
                System.out.println();
            }
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public Map copy()
    {
        Map newMap = new Map(width, height, depth, chrono);

        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++)
            {
                for(int z = 0; z < depth; z++)
                    for(int w = 0; w < chrono; w++)
                        newMap.grid[x][y][z][w].content = grid[x][y][z][w].content;
            }

        return newMap;
    }


    public int countActiveCubes()
    {
        int count = 0;

        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++)
            {
                for(int z = 0; z < depth; z++)
                    for(int w = 0; w < chrono; w++)
                        if(grid[x][y][z][w].content == '#')
                            count++;
            }
        return count;
    }

    public Map expandBoundaries()
    {
        Map expandedMap = new Map(width+2, height+2, depth+2, chrono+2);
        expandedMap.merge(this);
        return expandedMap;

    }

    private void merge(Map smallerMap)
    {
        for(int y = 0; y < smallerMap.height; y++)
            for(int x = 0; x < smallerMap.width; x++)
            {
                for(int z = 0; z < smallerMap.depth; z++)
                    for(int w = 0; w < smallerMap.chrono; w++)
                       grid[x+1][y+1][z+1][w+1].content = smallerMap.get(x, y, z, w).content;
            }
    }


}
