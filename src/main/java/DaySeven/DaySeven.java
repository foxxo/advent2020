package DaySeven;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class DaySeven {

    static List<Bag> bagRules = new ArrayList();

    public static void main(String[] args) throws IOException {

        List<String> inputLines = AdventUtil.readInputLines("input7");
        extractBags(inputLines);

        Set<Bag> roots = findBag("shiny gold").findRoots();

        System.out.println("All " + roots.size() + " roots of shiny gold: " + roots);

    }

    static void extractBags(List<String> input) {

        for (String s : input) {
            bagRules.add(new Bag(s));
        }

        for (Bag b : bagRules)
        {
            List<String> containedColors = Arrays.asList(b.rule.trim().split(","));
            for(String s : containedColors)
            {
                s = s.trim();
                if(s.contains("no other bags"))
                    continue;
                int numBags = Integer.parseInt(String.valueOf(s.charAt(0)));

                String color = s.substring(s.indexOf(" ")+1, s.indexOf(" bag"));

                Bag c = findBag(color);

                c.parents.add(b);
                b.children.put(c, numBags);
            }
        }

    }

    private static Bag findBag(String color)
    {
        for(Bag b : bagRules)
        {
            if(b.color.equals(color))
                return b;
        }
        return null;
    }



}
