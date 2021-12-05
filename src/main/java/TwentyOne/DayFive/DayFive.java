package TwentyOne.DayFive;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DayFive {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("2021/input5");

       List<Site> activeSites = new ArrayList();

       for(String l : inputLines)
       {
           List<String> endpoints = Arrays.asList(l.split(" -> "));

           Site start = new Site(endpoints.get(0));
           Site end = new Site(endpoints.get(1));

           if(start.x == end.x)
           {
               int direction = Integer.signum(end.y - start.y);
               for(int i = start.y; i != end.y; i += direction)
               {
                   Site s = new Site(start.x, i);
                   addOrIncrement(activeSites, s);
               }
               addOrIncrement(activeSites, end);
           }
           if(start.y == end.y)
           {
               int direction = Integer.signum(end.x - start.x);
               for(int i = start.x; i != end.x; i += direction)
               {
                   Site s = new Site(i, start.y);
                   addOrIncrement(activeSites, s);
               }
               addOrIncrement(activeSites, end);
           }
       }
       List<Site> multiSites = activeSites.stream().filter(it -> it.activity > 1).collect(Collectors.toList());
//        for(Site s : multiSites)
//        {
//            System.out.println(s);
//        }

        System.out.println("Multi-active sites: " + multiSites.size());


    }

    static void addOrIncrement(List<Site> list, Site target)
    {
        int foundIndex = list.indexOf(target);
        if(foundIndex != -1) {
            list.get(foundIndex).activity++;
//                       System.out.println("Updating " + activeSites.get(foundIndex));
        }
        else {
            list.add(target);
//                       System.out.println("Adding " + s);
        }
    }

}
