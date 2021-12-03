package TwentyOne.DayThree;

import AdventUtil.AdventUtil;
import TwentyOne.DayTwo.Sub;
import com.sun.org.apache.xalan.internal.xsltc.dom.BitArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

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
        for(int i = 0; i < 12; i++)
        {
            common.add(getCommonValue(inputBits, i));
        }

        int gamma = Integer.parseInt(toBinary(common), 2);
        System.out.println("gamma (from  " + common + ") : " + gamma);

        flipBits(common);

        int epsilon = Integer.parseInt(toBinary(common), 2);
        System.out.println("epsilon (from  " + common + ") : " + epsilon);

        System.out.println("Product: "  + epsilon * gamma);

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

        return trueCount >= bits.size() / 2;
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
