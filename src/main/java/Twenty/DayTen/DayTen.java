package Twenty.DayTen;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DayTen {

    public static void main(String[] args) throws IOException {

        List<Integer> inputLines = AdventUtil.readInputLines("2020/input10").stream().map(Integer::parseInt).collect(Collectors.toList());

        inputLines.add(0);
        Collections.sort(inputLines);
        inputLines.add(inputLines.get(inputLines.size()-1)+3);

        HashMap<Integer, Integer> joltageDifferences = getJoltageDifferences(inputLines);

        System.out.println("Joltage diffs: " + joltageDifferences);
        System.out.println("1's x 3's: " + joltageDifferences.get(1) * joltageDifferences.get(3));
        System.out.println("Sorted array:     " + inputLines);
        System.out.println("Chunks: " + chunkify(inputLines));
        long permutations = 1;
        for(List<Integer> t : chunkify(inputLines))
        {
            permutations *= tribonacci(t.size());
        }

        System.out.println("Permutations: " + permutations);
    }

    private static HashMap<Integer, Integer> getJoltageDifferences(List<Integer> intList)
    {
        HashMap<Integer, Integer> differences = new HashMap();

        differences.put(1, 0);
        differences.put(2, 0);
        differences.put(3, 0);

        for(int i = 0; i < intList.size()-1; i++)
        {
            int diff = intList.get(i+1) - intList.get(i);
            differences.put(diff, differences.get(diff)+1);
        }

        return differences;
    }

    private static List<List<Integer>> chunkify(List<Integer> intList) {
        ArrayList<List<Integer>> chunks = new ArrayList();
        List<Integer> currentChunk = new ArrayList();

        for (int i = 0; i < intList.size()-1; i++)
        {
            int diff = intList.get(i+1) - intList.get(i);
            currentChunk.add(intList.get(i));
            if(diff == 3)
            {
                chunks.add(new ArrayList<>(currentChunk));
                currentChunk = new ArrayList();
            }

        }
        int diff = intList.get(intList.size()-1) - intList.get(intList.size()-2);
        if(diff == 3)
        {
            chunks.add(Arrays.asList(intList.get(intList.size()-1)));
        }
        else
        {
            chunks.get(chunks.size()-1).add(intList.get(intList.size()-1));
        }
        return chunks;
    }

    private static long tribonacci(int l)
    {
        if(l <= 1)
            return 1;
        if (l == 2)
            return 1;
        if(l == 3)
            return 2;
        return tribonacci(l -1 ) + tribonacci(l -2) + tribonacci( l - 3);
    }


}