package Twenty.DayNine;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DayNine {

    public static void main(String[] args) throws IOException {

        List<Long> inputLines = AdventUtil.readInputLines("2020/input9").stream().map(Long::parseLong).collect(Collectors.toList());

        int t = analyzeList(inputLines, 25);
        findSumRange(inputLines, t);

    }

    private static int analyzeList(List<Long> inputLines, int preambleLength) {
        ArrayList<Long> input = new ArrayList(inputLines);
        for(; preambleLength < input.size(); input.remove(0))
        {
            if(!isValid(input, preambleLength))
            {
                System.out.println("First invalid number: " + input.get(preambleLength) + " at index " + inputLines.indexOf(input.get(preambleLength)));
                return inputLines.indexOf(input.get(preambleLength));
            }
        }
        return -1;
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

    public static void findSumRange(List<Long> input, int targetIndex)
    {
        for(int i = 0; i < targetIndex; i++)
        {
            long sum = input.get(i);
            int j = i + 1;
            while(sum < input.get(targetIndex) && j < targetIndex)
            {
                sum += input.get(j);

                if(sum == input.get(targetIndex)) {
                    List<Long> subrange = new ArrayList(input.subList(i, j));
                    Collections.sort(subrange);

                    System.out.println("Encryption weakness: " + (subrange.get(0) + subrange.get(subrange.size()-1)));
                    return;
                }

                j++;
            }

        }
    }

}
