package Twenty.DayFourteen;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DayFourteen {

    public static void main(String[] args) throws IOException {

        List<String> inputLines = AdventUtil.readInputLines("2020/input14");
        HashMap<Integer, Character> bitMask = readBitMask(inputLines.get(0));
        HashMap<Long, String> mem = new HashMap<>();

        partOne(inputLines, bitMask, mem);
        partTwo(inputLines, bitMask, mem);


    }

    private static void partTwo(List<String> inputLines, HashMap<Integer, Character> bitMask, HashMap<Long, String> mem) {

        for(String inputLine : inputLines)
        {
            List<String> splitLine = Arrays.asList(inputLine.split(" = "));
            if (splitLine.get(0).equals("mask")) {
                bitMask = readBitMask(splitLine.get(1));
            } else {
                String binary = Integer.toBinaryString(Integer.parseInt(splitLine.get(1)));
                binary = prepend(binary, 36);
                int reg = Integer.parseInt(splitLine.get(0).substring(splitLine.get(0).indexOf("[") + 1, splitLine.get(0).length() - 1));
                List<String> binaryAddresses = new ArrayList<>();
                binaryAddresses.add("");

                char[] memChar = prepend(Integer.toBinaryString(reg), 36).toCharArray();
                bitMask.forEach((place, value) ->
                {
                    switch(value)
                    {
                        case '1':
                            for(int i = 0; i < binaryAddresses.size(); i++)
                            {
                                binaryAddresses.set(i, binaryAddresses.get(i) + value);
                            }
                            break;
                        case '0':
                            for(int i = 0; i < binaryAddresses.size(); i++)
                            {
                                binaryAddresses.set(i, binaryAddresses.get(i) + memChar[place]);
                            }
                            break;
                        case 'X': {
                            int startSize = binaryAddresses.size();
                            for (int i = 0; i < startSize; i++) {
                                binaryAddresses.add(binaryAddresses.get(i) + '1');
                                binaryAddresses.set(i, binaryAddresses.get(i) + '0');
                            }
                            break;
                        }
                    }
                });
                System.out.println("Addresses to modify: " + binaryAddresses + " (");
                for(String a : binaryAddresses)
                {
                    long register = Long.parseLong(a, 2);
                    mem.put(register, binary);
                    System.out.print(register + ", ");

                }
                System.out.println(")");
            }

        }
        final long[] sum = {0};
        mem.forEach( (register, value )->
        {

            System.out.println("register " + register + ": " + value);
            sum[0] += Long.parseLong(value, 2);
        });
        System.out.println("Sum of memory: " + sum[0]);
    }

    private static void partOne(List<String> inputLines, HashMap<Integer, Character> bitMask, HashMap<Long, String> mem) {
        long sum = 0;


        for (String inputLine : inputLines) {

            List<String> splitLine = Arrays.asList(inputLine.split(" = "));
            if (splitLine.get(0).equals("mask")) {
                bitMask = readBitMask(splitLine.get(1));
            } else {
                String binary = Integer.toBinaryString(Integer.parseInt(splitLine.get(1)));
                binary = prepend(binary, 36);
                String maskedString = applyMask(binary.toCharArray(), bitMask);
                long reg = Long.parseLong(splitLine.get(0).substring(splitLine.get(0).indexOf("[") + 1, splitLine.get(0).length() - 1));
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
        bitMask.forEach((place, value) ->
            {
                if(value != 'X')
                s[place] = value;
            });
        return new String(s);
    }

    private static HashMap<Integer, Character> readBitMask(String input) {
        HashMap<Integer, Character> mask = new HashMap<>();
        for(int i = 0; i < input.length(); i++)
        {
//            if(input.charAt(i) != ('X'))
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
