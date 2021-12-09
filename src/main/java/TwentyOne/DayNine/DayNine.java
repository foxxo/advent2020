package TwentyOne.DayNine;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static jdk.vm.ci.meta.JavaKind.Char;

public class DayNine {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("2021/input9");

        int height = inputLines.size();
        int width = inputLines.get(0).length();
        Node[][] grid = new Node[width][height];

       for(int y = 0; y < height; y++) {
           char[] digits = inputLines.get(y).toCharArray();
           for (int x = 0; x < width; x++) {
               grid[x][y] = new Node(x, y, grid);
               grid[x][y].h = Integer.parseInt(""+digits[x]);
           }
       }

       List<Node> lowPoints = new ArrayList();

       for(int y = 0; y < height; y++) {
           for (int x = 0; x < width; x++)
           {
               System.out.print(grid[x][y]);
               List<Node> neighbors = grid[x][y].getNeighbors();
               boolean low = true;
               for(Node n: neighbors)
               {
                   if (n.h <= grid[x][y].h) {
                       low = false;
                       break;
                   }
               }
               if(low)
                   lowPoints.add(grid[x][y]);

           }
           System.out.println();
       }

        System.out.println(lowPoints);

       int riskSum = lowPoints.stream().map(it -> it.h + 1).reduce(0, (a,b)->a+b);

        System.out.println("Risk level sum: " + riskSum);

        List<List<Node>> basins = new ArrayList<>();

        for(Node l : lowPoints)
        {

            List<Node> basin = l.getBasinBuddies();
            basin.add(l);
            int size = basin.size();
            for(int i = 0; i < size; i++)
            {
                basin.addAll(basin.get(i).getBasinBuddies().stream().filter(it -> !basin.contains(it)).collect(Collectors.toList()));
                size = basin.size();
            }
            basins.add(basin);
        }

        Collections.sort(basins, (Comparator<List>) (a1, a2) -> a2.size() - a1.size());
        System.out.println("Basins: " + basins);

        System.out.println("Basin size product: " + basins.subList(0,3).stream().map(it -> it.size()).reduce(1,(a,b)->a*b));

    }

}

 class Node{
    int x, y, h;
    Node[][] grid;

    public Node(int x, int y, Node[][] grid)
    {
        this.x = x; this.y = y; h = 0;
        this.grid = grid;
    }

    public List<Node> getNeighbors()
    {
        List<Node> neighbors = new ArrayList();
        if(x > 0)
            neighbors.add(grid[x-1][y]);
        if(x < grid.length-1)
            neighbors.add(grid[x+1][y]);
        if(y > 0)
            neighbors.add(grid[x][y-1]);
        if(y < grid[0].length-1)
            neighbors.add(grid[x][y+1]);
        return neighbors;
    }

    public List<Node> getBasinBuddies()
    {
        return getNeighbors().stream().filter(it -> it.h != 9 && it.h > h).collect(Collectors.toList());
    }



    public String toString()
    {
        return ""+h;
    }



}