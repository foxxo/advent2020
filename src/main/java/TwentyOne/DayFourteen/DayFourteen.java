package TwentyOne.DayFourteen;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayFourteen {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("2021/test14");
        String polymer = inputLines.get(0);

        inputLines.remove(0);
        inputLines.remove(0);

        List<Rule> rules = new ArrayList<>();

        for(String line : inputLines)
        {
            rules.add(new Rule(line));
        }

        List<Replacement> allReplacements = new ArrayList<>();

        for(Rule r : rules)
        {
            allReplacements.addAll(r.apply(polymer));
        }

        allReplacements.sort(Comparator.comparingInt(a -> a.index));

        int adjustment = 0;
        for(Replacement r : allReplacements)
        {
            polymer = polymer.substring(0, r.index+adjustment) + r.newText + polymer.substring(r.index+adjustment);
            adjustment++;
        }


        System.out.println(polymer);

    }
}

class Rule
{
    String insert;
    String match;

    public Rule(String line)
    {
        match = line.substring(0, 2);
        insert = "" + line.charAt(6);
    }

    public List<Replacement> apply(String line)
    {
        Pattern p = Pattern.compile(match);
        Matcher m = p.matcher(line);

        List<Replacement> replacements = new ArrayList<>();

        while (m.find())
        {
            replacements.add(new Replacement(m.start()+1, insert));
        }

        return replacements;
    }

}

class Replacement
{
    String newText;
    int index;

    public Replacement(int i, String nt)
    {
        index = i; newText = nt;
    }

}