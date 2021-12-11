package TwentyOne.DayThree;

import AdventUtil.AdventUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DayThree {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("2021/input3");

        List<List<Boolean>> inputBits = new ArrayList();

        for(String s : inputLines)
        {
            List<Boolean> word = new ArrayList();
            inputBits.add(word);
            for(char c : s.toCharArray())
            {
                if(c == '1')
                    word.add(true);
                else
                    word.add(false);
            }
        }

        List<Boolean> common = new ArrayList();
        for(int i = 0; i < inputBits.get(0).size(); i++)
        {
            common.add(getCommonValue(inputBits, i));
        }

        int gamma = Integer.parseInt(toBinary(common), 2);
        System.out.println("gamma (from  " + common + ") : " + gamma);

        flipBits(common);

        int epsilon = Integer.parseInt(toBinary(common), 2);
        System.out.println("epsilon (from  " + common + ") : " + epsilon);

        System.out.println("Product: "  + epsilon * gamma);

        List<List<Boolean>> oxy = new ArrayList(inputBits);
        List<List<Boolean>> scrub = new ArrayList(inputBits);

        flipBits(common);

        for(int i = 0; i < common.size(); i++)
        {
            int index = i;
            if(oxy.size() > 1)
                oxy.removeAll(oxy.stream().filter(w -> w.get(index) != common.get(index)).collect(Collectors.toList()));

            common.clear();
            for(int j = 0; j < oxy.get(0).size(); j++)
            {
                common.add(getCommonValue(oxy, j));
            }
        }

        for(int i = 0; i < common.size(); i++)
        {
            int index = i;
            if(scrub.size() > 1)
                scrub.removeAll(scrub.stream().filter(w -> w.get(index) == common.get(index)).collect(Collectors.toList()));

            common.clear();
            for(int j = 0; j < oxy.get(0).size(); j++)
            {
                common.add(getCommonValue(scrub, j));
            }
        }

        int ox = Integer.parseInt(toBinary(oxy.get(0)), 2);
        System.out.println("ox generator (from  " + oxy + ") : " + ox);

        int scrubber = Integer.parseInt(toBinary(scrub.get(0)), 2);
        System.out.println("co2 scrubber (from  " + scrub + ") : " + scrubber);

        System.out.println("Product: "  + ox * scrubber);

    }

    public static void flipBits(List<Boolean> list)
    {
        for(int i = 0; i < list.size(); i++)
        {
            list.set(i, !list.get(i));
        }
    }

    static boolean getCommonValue(List<List<Boolean>> bits, int column)
    {
        int trueCount = 0;

        for(List<Boolean> word : bits)
        {
            if(word.get(column))
                trueCount++;
        }

        return trueCount >= bits.size() / 2f;
    }

    static String toBinary(List<Boolean> bools)
    {
        String out = "";
        for(Boolean b : bools)
        {
            out += b ? "1" : "0";
        }
        return out;
    }

}
