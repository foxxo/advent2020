package TwentyOne.Thirteen;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DayThirteen {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("2021/input13");

        List<Dot> dots = new ArrayList<>();

        int foldStart = 0;
        for(String line : inputLines)
        {
            foldStart++;
            if(line.equals(""))
                break;
            dots.add(new Dot(Integer.parseInt(line.substring(0, line.indexOf(","))), Integer.parseInt(line.substring(line.indexOf(",")+1))));
        }

        System.out.println("Dots before folding: " + dots.size());

        for(int i = foldStart; i < inputLines.size(); i++)
        {
           int val = Integer.parseInt(inputLines.get(i).substring(inputLines.get(i).indexOf("=")+1));
           char axis = inputLines.get(i).charAt(inputLines.get(i).indexOf("=")-1);
           int offset = 0;

           ArrayList<Dot> newSheet = new ArrayList<>();
           for(Dot dot : dots)
           {
               if(dot.getValOnAxis(axis) > val) {
                   Dot nuDot = new Dot(dot.x, dot.y);
                   nuDot.setValOnAxis(axis, val - (nuDot.getValOnAxis(axis) - val));

                   if(nuDot.getValOnAxis(axis) < offset)
                       offset = nuDot.getValOnAxis(axis);

                   if(!newSheet.contains(nuDot)) {
                       newSheet.add(nuDot);
                       newSheet.remove(dot);
                   }
               }
               else
                   if(!newSheet.contains(dot))
                        newSheet.add(dot);
           }

            int finalOffset = offset;
            newSheet.forEach(it -> it.setValOnAxis(axis, it.getValOnAxis(axis) - finalOffset));

            dots = newSheet.stream().distinct().collect(Collectors.toList());
        }

        System.out.println("Dots after folding: " + dots.size());
        System.out.println(dots);

        Optional<Integer> maxX = dots.stream().max(Comparator.comparingInt(a -> a.x)).map(it -> it.x);
        Optional<Integer> maxY = dots.stream().max(Comparator.comparingInt(a -> a.y)).map(it -> it.y);
        int X = maxX.orElse(-1);
        int Y = maxY.orElse(-1);

        for(int y = 0; y <= Y; y++) {
            for (int x = 0; x <= X; x++) {
                if(dots.contains(new Dot(x,y)))
                    System.out.print("#");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }

    }

}

class Dot{
    int x, y;

    public Dot(int nx, int ny)
    {
        x = nx; y = ny;
    }

    public int getValOnAxis(char axis)
    {
        return axis=='x'? x : y;
    }

    public void setValOnAxis(char axis, int v)
    {
        if(axis == 'x')
            x = v;
        else
            y = v;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dot site = (Dot) o;
        return x == site.x &&
                y == site.y;
    }

    public String toString()
    {
        return "(" + x + "," + y + ")";
    }


}
