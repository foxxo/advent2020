package TwentyOne.DaySix;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DaySix {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("2021/input6");
        int duration = 256;

        List<BigInteger> timeStack = new ArrayList(Collections.nCopies(9, BigInteger.ZERO));

        List<BigInteger> finalTimeStack = timeStack;
        Arrays.stream(inputLines.get(0).split(","))
                .map(BigInteger::new)
                .forEach(it -> finalTimeStack.set(it.intValue(), finalTimeStack.get(it.intValue()).add(BigInteger.ONE)));
        timeStack = finalTimeStack;

        for(int i = 0; i < duration; i++)
        {
            System.out.println("Day " + (i));
            displayPop(timeStack);
            timeStack = nextDay(timeStack);
        }

        System.out.println("Population after " + duration + " days: ");
        System.out.println(timeStack.stream().reduce(BigInteger.ZERO, (a,b) -> a.add(b)));

    }

    static List<BigInteger> nextDay(List<BigInteger> day)
    {
        List<BigInteger> newDay = new ArrayList(Collections.nCopies(9, BigInteger.ZERO));

        newDay.set(8, day.get(0));
        for(int i = 1; i < day.size(); i++)
        {
            newDay.set(i-1, day.get(i));
        }
        newDay.set(6, newDay.get(6).add(day.get(0)));
        return newDay;
    }

    static void displayPop(List<BigInteger> day)
    {
        System.out.println("0, 1, 2, 3, 4, 5, 6, 7");
        System.out.println(day);
        System.out.println(day.stream().reduce(BigInteger.ZERO, (a,b) -> a.add(b)));
    }
}
