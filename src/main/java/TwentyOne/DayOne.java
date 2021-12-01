package TwentyOne;

import AdventUtil.AdventUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayOne {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("2021/input1");

        int increases = 0;
        for(int i = 1; i < inputLines.size() -2; i++)
        {

            if(sumRange(inputLines, i-1) < sumRange(inputLines, i))
                increases++;
        }

        System.out.println("Increases: " + increases);
    }

    static int sumRange(List<String> lines, int startIndex)
    {
        return lines.subList(startIndex, startIndex + 3).stream().map( Integer::parseInt).reduce(0, Integer::sum);
    }
}
