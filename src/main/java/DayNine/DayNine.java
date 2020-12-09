package DayNine;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DayNine {

    public static void main(String[] args) throws IOException {

        List<Long> inputLines = AdventUtil.readInputLines("input9").stream().map(Long::parseLong).collect(Collectors.toList());

        analyzeList(inputLines, 25);


    }

    private static void analyzeList(List<Long> inputLines, int preambleLength) {
        for(; preambleLength < inputLines.size(); inputLines.remove(0))
        {
            if(!isValid(inputLines, preambleLength))
            {
                System.out.println("First invalid number: " + inputLines.get(preambleLength) + " at index " + preambleLength);
                break;
            }
        }
    }

    public static boolean isValid(List<Long> input, int preambleLength)
    {
        ArrayList<Long> previousValues = new ArrayList(input.subList(0, preambleLength));
        long target = input.get(preambleLength);

        Collections.sort(previousValues);

        if(target >= previousValues.get(preambleLength -1)*2)
            return false;

        for(int i = 0; i < preambleLength-1; i++)
            for(int j = i +1; j < preambleLength; j++)
            {
                if(previousValues.get(i) + previousValues.get(j) == target)
                    return true;
            }
        return false;
    }


}
