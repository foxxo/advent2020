package TwentyOne.DayFive;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                   int foundIndex = activeSites.indexOf(s);
                   if(foundIndex != -1) {
                       activeSites.get(foundIndex).activity++;
                       System.out.println("Updating " + activeSites.get(foundIndex));
                   }
                   else {
                       activeSites.add(s);
                       System.out.println("Adding " + s);
                   }
               }
               activeSites.add(end);
           }
           if(start.y == end.y)
           {
               int direction = Integer.signum(end.x - start.x);
               for(int i = start.x; i != end.x; i += direction)
               {
                   Site s = new Site(i, start.y);
                   int foundIndex = activeSites.indexOf(s);
                   if(foundIndex != -1) {
                       activeSites.get(foundIndex).activity++;
                       System.out.println("Updating " + activeSites.get(foundIndex));
                   }
                   else {
                       activeSites.add(s);
                       System.out.println("Adding " + s);
                   }
               }
               activeSites.add(end);
           }
       }

    }


}
