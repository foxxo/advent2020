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

        List<Boolean> inputBits = new ArrayList();

        int bitIndex = 0;
        int lineLen = 12;
        for(String s : inputLines)
        {
            for(char c : s.toCharArray())
            {
                if(c == '1')
                    inputBits.add(true);
                else
                    inputBits.add(false);
                bitIndex++;
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

    static boolean getCommonValue(List<Boolean> bits, int column)
    {
        int index = column;
        int trueCount = 0;
        while(index < bits.size())
        {
            if(bits.get(index))
                trueCount++;
            index += 12;
        }

        return trueCount >= bits.size() / 24;
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
