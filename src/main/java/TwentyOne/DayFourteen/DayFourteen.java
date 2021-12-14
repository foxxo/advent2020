package TwentyOne.DayFourteen;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DayFourteen {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("2021/input14");
        String polymer = inputLines.get(0);

        inputLines.remove(0);
        inputLines.remove(0);

        List<Rule> rules = new ArrayList<>();

        for(String line : inputLines)
        {
            rules.add(new Rule(line));
        }


        for(int i = 0; i < 40; i++) {
            System.out.println("Starting Iteration " + (i + 1) + "...");
            List<Replacement> allReplacements = new ArrayList<>();
            Instant start = Instant.now();

            for (Rule r : rules) {
                allReplacements.addAll(r.apply(polymer));
            }

            allReplacements.sort(Comparator.comparingInt(a -> a.index));

            int adjustment = 0;
            for (Replacement r : allReplacements) {
                polymer = polymer.substring(0, r.index + adjustment) + r.newText + polymer.substring(r.index + adjustment);
                adjustment++;
            }

            Instant stop = Instant.now();
            System.out.println("Duration: " + Duration.between(start, stop));
        }

        System.out.println(polymer);

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < polymer.length(); i++) {
            char c = polymer.charAt(i);
            map.merge(c, 1, Integer::sum);
        }

       Map<Character, Integer> sortedMap = map.entrySet()
               .stream()
               .sorted(Comparator.comparing(Map.Entry::getValue))
               .collect(Collectors.toMap(
                       Map.Entry::getKey,
                       Map.Entry::getValue,
                       (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(sortedMap);


    }
}

class Rule
{
    String insert;
    String match;

    public Rule(String line)
    {
        match = "(?=" + line.substring(0, 2) + ")";
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