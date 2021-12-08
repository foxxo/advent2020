package TwentyOne.DayEight;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class DayEight {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("2021/input8");

        List<Integer> numberSizes = Arrays.asList(6, 2, 5, 5, 4, 5, 6, 3, 7, 6);

        for(String s : inputLines)
        {

            List<List<String>> possibleWirings = new ArrayList(Collections.nCopies(10, new ArrayList()));


            List<String> displays = Arrays.asList(s.substring(s.indexOf("| ")+2).split(" "));
            for(String n : displays)
            {
                int l = n.length();
                for(int i = 0; i < numberSizes.size(); i++)
                {
                    if(l == numberSizes.get(i))
                        possibleWirings.get(i).add(n);
                }
            }

        }

    }
}
