package TwentyOne.DayEight;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class DayEight {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("2021/input8");

        List<Integer> numberSizes = Arrays.asList(6, 2, 5, 5, 4, 5, 6, 3, 7, 6);

        int megasum = 0;
        for (String s : inputLines) {

            List<List<String>> possibleWirings = new ArrayList();
            for(int i = 0; i < 10; i++)
                possibleWirings.add(new ArrayList());


            List<String> numbers = Arrays.asList(s.substring(0, s.indexOf(" | ")).split(" "));
            for (String n : numbers) {
                int l = n.length();
                for (int i = 0; i < numberSizes.size(); i++) {
                    if (l == numberSizes.get(i))
                        possibleWirings.get(i).add(n);
                }

            }

            identifyThree(possibleWirings);
            identifyFiveAndTwo(possibleWirings);
            identifySix(possibleWirings);
            identifyZeroAndNine(possibleWirings);

            List<String> displays = Arrays.asList(s.substring( s.indexOf("| ")+2).split(" "));
            int sum = 0;
            for(int d = 0; d < displays.size(); d++)
            {
                sum += parseDisplay(displays.get(d), possibleWirings) * (Math.pow(10,(displays.size()-1 - d)));
            }

            System.out.println(sum);
            megasum += sum;
        }

        System.out.println("total: " + megasum);
    }

    private static int parseDisplay(String display, List<List<String>> wirings){
        for(List<String> wiring : wirings) {
            if (wiring.get(0).length() == display.length() && containsAllChars(display, wiring.get(0)))
                return wirings.indexOf(wiring);
        }

        return -1;
    }

    private static void identifyZeroAndNine(List<List<String>> possibleWirings) {
        String wiringTwo = possibleWirings.get(2).get(0);
        String wiringThree = possibleWirings.get(3).get(0);
        String wiringZero = "";
        String bottomLeftLine = "";

        for(char c : wiringTwo.toCharArray())
        {
            if(!containsAllChars(wiringThree, "" + c))
                bottomLeftLine += c;
        }

        for(String zeroes: possibleWirings.get(0))
        {
            if(zeroes.contains(bottomLeftLine)) {
                wiringZero = zeroes;
                possibleWirings.get(9).remove(wiringZero);
                possibleWirings.get(0).clear();
                possibleWirings.get(0).add(wiringZero);
                break;
            }
        }
    }

    private static void identifySix(List<List<String>> possibleWirings) {
        String wiringFour = possibleWirings.get(4).get(0);
        String wiringFive = possibleWirings.get(5).get(0);
        String wiringSix = "";
        String topRightLine = "";

        for(char c : wiringFour.toCharArray())
        {
            if(!containsAllChars(wiringFive, "" + c))
                topRightLine += c;
        }

        for(String sixes: possibleWirings.get(6))
        {
            if(!sixes.contains(topRightLine)) {
                wiringSix = sixes;
                possibleWirings.get(0).remove(wiringSix);
                possibleWirings.get(9).remove(wiringSix);
                possibleWirings.get(6).clear();
                possibleWirings.get(6).add(wiringSix);
                break;
            }
        }
    }

    private static void identifyFiveAndTwo(List<List<String>> possibleWirings) {
        String wiringThree = possibleWirings.get(3).get(0);
        String wiringFour = possibleWirings.get(4).get(0);
        String wiringFive = "";
        String topLeftLine = "";

        for(char c : wiringFour.toCharArray())
        {
            if(!containsAllChars(wiringThree, "" + c))
                topLeftLine += c;
        }

        for(String fives: possibleWirings.get(5))
        {
            if(fives.contains(topLeftLine)) {
                wiringFive = fives;
                possibleWirings.get(2).remove(wiringFive);
                possibleWirings.get(5).clear();
                possibleWirings.get(5).add(wiringFive);
                break;
            }
        }
    }

    private static void identifyThree(List<List<String>> possibleWirings) {
        String wiringOne = possibleWirings.get(1).get(0);
        String wiringThree = "";
        for(String wiring : possibleWirings.get(3))
        {
            if(containsAllChars(wiring, wiringOne))
                wiringThree = wiring;
        }
        possibleWirings.get(3).clear();
        possibleWirings.get(3).add(wiringThree);
        possibleWirings.get(5).remove(wiringThree);
        possibleWirings.get(2).remove(wiringThree);
    }

    static Set<Character> stringToCharacterSet(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        return set;
    }

    static boolean containsAllChars(String string, String chars) {
        return stringToCharacterSet(string).containsAll(stringToCharacterSet(chars));
    }


}
