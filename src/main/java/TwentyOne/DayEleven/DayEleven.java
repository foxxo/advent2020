package TwentyOne.DayEleven;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DayEleven {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("2021/input11");

        int height = inputLines.size();
        int width = inputLines.get(0).length();
        Node[][] grid = new Node[width][height];

        for (int y = 0; y < height; y++) {
            char[] digits = inputLines.get(y).toCharArray();
            for (int x = 0; x < width; x++) {
                grid[x][y] = new Node(x, y, grid);
                grid[x][y].energy = Integer.parseInt("" + digits[x]);
            }
        }

        int numSteps = 0;
        int flashes = 0;

        while (numSteps >= 0) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
//                    System.out.print(grid[x][y]);
                    int stepFlashes = grid[x][y].boostAndFlashIfReady();
                    if(stepFlashes == 100) {

                        System.out.println("Syncronous flash at step " + (numSteps));
                        numSteps = -2;
                    }
                    flashes += stepFlashes;

                }
//                System.out.println();
            }
            numSteps++;
//            System.out.println("Flashes after step " + (100 - numSteps) + ": " + flashes);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    grid[x][y].spent = false;
                }
            }



        }
//        System.out.println("Final flash count: " + flashes);
    }
}

class Node{
   int x, y, energy;
   boolean spent;
   Node[][] grid;

   public Node(int x, int y, Node[][] grid)
   {
       this.x = x; this.y = y; energy = 0;
       this.grid = grid;
   }

   public List<Node> getNeighbors()
   {
       List<Node> neighbors = new ArrayList();
       if(x > 0) {
           neighbors.add(grid[x - 1][y]);
           if(y > 0)
               neighbors.add(grid[x-1][y-1]);
           if(y < grid[0].length-1)
               neighbors.add(grid[x-1][y+1]);

       }
       if(x < grid.length-1) {
           neighbors.add(grid[x + 1][y]);
           if(y > 0)
               neighbors.add(grid[x+1][y-1]);
           if(y < grid[0].length-1)
               neighbors.add(grid[x+1][y+1]);
       }
       if(y > 0)
           neighbors.add(grid[x][y-1]);
       if(y < grid[0].length-1)
           neighbors.add(grid[x][y+1]);

       return neighbors;
   }

    public int boostAndFlashIfReady()
    {
        if(!spent) {
            energy++;
            if (energy > 9) {
                spent = true;
                energy = 0;
                return getNeighbors().stream().map(Node::boostAndFlashIfReady).reduce(1, Integer::sum);
            }
        }
        return 0;
    }


   public List<Node> getBasinBuddies()
   {
       return getNeighbors().stream().filter(it -> it.energy != 9 && it.energy > energy).collect(Collectors.toList());
   }



   public String toString()
   {
       return ""+ energy;
   }



}