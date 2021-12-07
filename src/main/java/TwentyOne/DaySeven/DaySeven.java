package TwentyOne.DaySeven;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DaySeven {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("2021/input7");
        List<Integer> crabs = Arrays.stream(inputLines.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());

        Collections.sort(crabs);
        System.out.println(crabs);

        int min = crabs.get(0);
        int max = crabs.get(crabs.size()-1);

        Map<Integer, Integer> fuelUsages = new HashMap<>();

        for(int i = min; i <= max; i++)
        {
            int f = 0;
            for(int c : crabs)
            {
                f += Math.abs(i - c);
            }
            fuelUsages.put(i, f);
        }

        List<Integer> fuelOnly = new ArrayList<>(fuelUsages.values());
        System.out.println( "Fuel use: " + fuelOnly);
        Collections.sort(fuelOnly);

        System.out.println("Minimum fuel usage: " + fuelOnly.get(0));
    }


}
