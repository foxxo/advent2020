package DayTen;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DayTen {

    public static void main(String[] args) throws IOException {

        List<Integer> inputLines = AdventUtil.readInputLines("short10").stream().map(Integer::parseInt).collect(Collectors.toList());

        inputLines.add(0);
        Collections.sort(inputLines);
        inputLines.add(inputLines.get(inputLines.size()-1)+3);

        HashMap<Integer, Integer> joltageDifferences = getJoltageDifferences(inputLines);

        System.out.println("Joltage diffs: " + joltageDifferences);
        System.out.println("1's x 3's: " + joltageDifferences.get(1) * joltageDifferences.get(3));

    }

    private static HashMap<Integer, Integer> getJoltageDifferences(List<Integer> intList)
    {
        HashMap<Integer, Integer> differences = new HashMap();
        differences.put(1, 0);
        differences.put(2, 0);
        differences.put(3, 0);

        for(int i = 0; i < intList.size()-1; i++)
        {
            int diff = intList.get(i+1) - intList.get(i);
            differences.put(diff, differences.get(diff)+1);
        }
        return differences;
    }
}