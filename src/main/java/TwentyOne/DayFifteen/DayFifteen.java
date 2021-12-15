package TwentyOne.DayFifteen;

import AdventUtil.AdventUtil;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.*;

public class DayFifteen {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("2021/input15");
        int height = inputLines.size();
        int width = inputLines.get(0).length();
        Node[][] grid = new Node[width*5][height*5];

        for (int y = 0; y < height; y++) {
            char[] digits = inputLines.get(y).toCharArray();
            for (int x = 0; x < width; x++) {
                grid[x][y] = new Node(x, y, grid, Integer.parseInt("" + digits[x]));
                grid[x][y].distance = Integer.MAX_VALUE;
            }
        }

        height *= 5;
        width *= 5;


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[x][y] = new Node(x, y, grid, getRisk(grid, x, y));
                grid[x][y].distance = Integer.MAX_VALUE;
            }
        }


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(grid[x][y].risk);
            }
            System.out.println();
        }

        System.out.println(dijkstra(grid, grid[height-1][width-1]));

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                    int l = 4;
                    while( l > Integer.toString(grid[x][y].distance).length()) {
                        System.out.print(".");
                        l--;
                    }
                    System.out.print(grid[x][y].distance);
            }
            System.out.println();
        }

    }

    public static int dijkstra(Node[][] grid, Node target)
    {
        PriorityQueue<Node> next = new PriorityQueue<>(Comparator.comparingInt(u -> u.distance));
        grid[0][0].distance = 0;
        next.add(grid[0][0]);
        while (!next.isEmpty())
        {
            Node current = next.poll();
            if (current.visited)
            {
                continue;
            }

            current.visited = true;

            if (current == target)
            {
                return target.distance;
            }

            for (Node n : current.getNeighbors())
            {
                int alt = current.distance + n.risk;
                if (alt < n.distance)
                {
                    n.distance = alt;
                }

                if (n.distance != Integer.MAX_VALUE)
                {
                    next.add(n);
                }
            }
        }
        return target.distance;
    }

    public static int getRisk(Node[][] grid, int x, int y)
    {
        int xOffset = x / (grid.length/5);
        int yOffset = y / (grid.length/5);
        int newRisk = ((grid[x < (grid.length/5)? x : x % (grid.length/5)][y < (grid.length/5)? y : y % (grid.length/5)].risk ) + xOffset + yOffset);
        if(newRisk > 9)
            newRisk = newRisk % 9;
        return newRisk > 0 ? newRisk : 1;
    }
}

class Node {
    int x, y, risk, distance;
    boolean visited;
    Node[][] grid;

    public Node(int x, int y, Node[][] grid, int r) {
        this.x = x;
        this.y = y;
        risk = r;
        this.grid = grid;
    }


    public List<Node> getNeighbors() {
        List<Node> neighbors = new ArrayList();
        if (x > 0) {
            neighbors.add(grid[x - 1][y]);
//            if(y > 0)
//                neighbors.add(grid[x-1][y-1]);
//            if(y < grid[0].length-1)
//                neighbors.add(grid[x-1][y+1]);

        }
        if (x < grid.length - 1) {
            neighbors.add(grid[x + 1][y]);
//            if(y > 0)
//                neighbors.add(grid[x+1][y-1]);
//            if(y < grid[0].length-1)
//                neighbors.add(grid[x+1][y+1]);
        }
        if (y > 0)
            neighbors.add(grid[x][y - 1]);
        if (y < grid[0].length - 1)
            neighbors.add(grid[x][y + 1]);

        return neighbors;
    }

    public String toString() {
        return "" + risk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node site = (Node) o;
        return x == site.x &&
                y == site.y;
    }



}