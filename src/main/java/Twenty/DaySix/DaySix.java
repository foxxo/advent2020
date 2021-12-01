package Twenty.DaySix;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaySix {
    public static void main(String[] args) throws IOException {

        List<String> inputLines = AdventUtil.readInputLines("2020/input6");

        ArrayList<String> groups = extractGroups(inputLines);

        int sumOfGroupUniques = 0;
        int sumOfGroupCommons = 0;
        for(String s : groups)
        {
            sumOfGroupUniques += uniqueAnswersInGroup(s);
            sumOfGroupCommons += universalAnswersInGroup(s);

        }

        System.out.println("Sum of each group's unique answers: " + sumOfGroupUniques);
        System.out.println("Sum of each group's common answers: " + sumOfGroupCommons);

    }

    static ArrayList<String> extractGroups(List<String> inputLines)
    {
        ArrayList<String> groups = new ArrayList();
        int i = 0;
        while(i < inputLines.size())
        {
            String lineString = "";
            while(i < inputLines.size() && !inputLines.get(i).isEmpty())
            {
                lineString += inputLines.get(i) + " ";
                i++;
            }
            if(!lineString.isEmpty())
                groups.add(lineString);
            else
                i++;
        }
        return groups;
    }

    static int universalAnswersInGroup(String group)
    {
        System.out.println("Evaluating group : " + group);
        List<String> individuals = Arrays.asList(group.split(" "));
        System.out.println(("Individuals : " + individuals));
        int commonAnswers = 0;

        if(individuals.size() == 1) {
            commonAnswers = individuals.get(0).length();
            System.out.println("Common answers: " + commonAnswers + "\n");
            return commonAnswers;
        }

        for(String c : individuals.get(0).split(""))
        {
            int common = 0;
            for(String s : individuals.subList(1, individuals.size()))
            {
                if(s.contains(c))
                    common++;
            }
            if(common == individuals.size()-1)
                commonAnswers++;
        }
        System.out.println("Common answers: " + commonAnswers + "\n");
        return commonAnswers;

    }

    static int uniqueAnswersInGroup(String group)
    {
        String uniqueAnswers = "";
        for(String c : group.replace(" ", "").split(""))
        {
            if(!uniqueAnswers.contains(c))
            {
                uniqueAnswers = uniqueAnswers.concat(c);
            }
        }
        return uniqueAnswers.length();
    }
}
