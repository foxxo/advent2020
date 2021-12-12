package TwentyOne.DayTwelve;

import AdventUtil.AdventUtil;
import TwentyOne.DayFive.Site;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DayTwelve {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("2021/test12");

        List<Cave> caves = new ArrayList();

        for(String line: inputLines)
        {
           Cave cave1 = new Cave(line.substring(0, line.indexOf("-")));
           Cave cave2 = new Cave(line.substring(line.indexOf("-")+1));

            if(!caves.contains(cave1)) {
                caves.add(cave1);
            }
           else {
                cave1 = caves.get(caves.indexOf(cave1));
            }
            if(!caves.contains(cave2)) {
                caves.add(cave2);
            }
           else {
                cave2 = caves.get(caves.indexOf(cave2));
            }
            cave1.addPath(cave2);
            cave2.addPath(cave1);
        }

        System.out.println(caves);
    }
}

class Cave
{
    String name;
    boolean isBig;
    List<Cave> connections;
    
    public Cave(String n)
    {
        name = n;
        isBig = name.equals(name.toUpperCase());
    }
    
    public void addPath(Cave c)
    {
       if(!connections.contains(c))
       {
           connections.add(c);
       }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cave site = (Cave) o;
        return name.equals(site.name);
    }

    public String toString()
    {
        String conList = connections.stream().map(it -> it.name).reduce("", (a,b) -> a + ", " + b);
        return (name + ": " + conList);
    }

}
