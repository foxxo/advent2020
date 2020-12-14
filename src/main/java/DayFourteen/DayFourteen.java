package DayFourteen;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DayFourteen {

    public static void main(String[] args) throws IOException {

        List<String> inputLines = AdventUtil.readInputLines("input14");
        HashMap<Integer, Character> bitMask = readBitMask(inputLines.get(0));
        HashMap<Integer, String> mem = new HashMap<>();

        long sum = 0;


        for(int i = 0; i < inputLines.size(); i++)
        {

            List<String> splitLine = Arrays.asList(inputLines.get(i).split(" = "));
            if(splitLine.get(0).equals("mask"))
            {
                bitMask = readBitMask(splitLine.get(1));
            }
            else
            {
                String binary = Integer.toBinaryString(Integer.parseInt(splitLine.get(1)));
                binary = prepend(binary, 36);
                String maskedString = applyMask(binary.toCharArray(), bitMask);
                int reg = Integer.parseInt(splitLine.get(0).substring(splitLine.get(0).indexOf("[")+1, splitLine.get(0).length()-1));
                mem.put(reg, maskedString);
            }
        }

        for(String m : mem.values())
        {
            sum += Long.parseLong(m, 2);
        }

        System.out.println("Sum of all lines with bitmask = " + sum);

    }

    private static String applyMask(char[] s, HashMap<Integer, Character> bitMask) {
        bitMask.forEach((place, value) -> s[place] = value);
        return new String(s);
    }

    private static HashMap<Integer, Character> readBitMask(String input) {
        HashMap<Integer, Character> mask = new HashMap<>();
        for(int i = 0; i < input.length(); i++)
        {
            if(input.charAt(i) != ('X'))
                mask.put(i, input.charAt(i));
        }
        return mask;
    }

    private static String prepend(String binaryNumber, int bits)
    {
        while(binaryNumber.length() < bits)
        {
            binaryNumber = "0".concat(binaryNumber);
        }
        return binaryNumber;
    }

}
