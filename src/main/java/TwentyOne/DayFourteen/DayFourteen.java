package TwentyOne.DayFourteen;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class DayFourteen {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("2021/input14");
        String polymer = inputLines.get(0);

        HashMap<Character, Long> characterFrequency = new HashMap<>();

        for (int i = 0; i < polymer.length(); i++) {
            char c = polymer.charAt(i);
            characterFrequency.merge(c, 1L, Long::sum);
        }

        inputLines.remove(0);
        inputLines.remove(0);

        List<Rule> rules = new ArrayList<>();

        for(String line : inputLines)
        {
            rules.add(new Rule(line));
        }

        HashMap<String, Long> pairFrequencies = new HashMap<>();

        for(int i = 0; i < polymer.length()-1; i++)
        {
            pairFrequencies.merge(polymer.substring(i, i+2), 1L, Long::sum);
        }


        for(int i = 0; i < 40; i++) {
            System.out.println("Starting Iteration " + (i + 1) + "...");
            Instant start = Instant.now();

            List<HashMap<String, Long>> modifications = new ArrayList<>();
            for (Rule r : rules) {
                HashMap<String, Long> afterRule = r.apply(pairFrequencies, characterFrequency);
                modifications.add(afterRule);
            }

            for(HashMap<String, Long> mod : modifications)
            {
                mod.forEach(
                        (key, value) -> {
                                if(pairFrequencies.get(key) != mod.get(key))
                                {
                                    pairFrequencies.merge(key, value, Long::sum);
                                }
                                else
                                {
                                    if(mod.get(key) != null)
                                        pairFrequencies.merge(key, value, Long::sum);
                                }
                });
            }


            Instant stop = Instant.now();
            System.out.println("Duration: " + Duration.between(start, stop));
        }

        System.out.println(pairFrequencies);

       Map<Character, Long> sortedMap = characterFrequency.entrySet()
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
        match = line.substring(0, 2);
        insert = "" + line.charAt(6);
    }

    public HashMap<String, Long> apply(HashMap<String, Long> polymer, HashMap<Character, Long> charFreq)
    {
        HashMap<String, Long> newMap = new HashMap<>();

        for(Map.Entry<String, Long> entry: polymer.entrySet())
        {
            if(entry.getKey().equals(match)) {
                newMap.put(entry.getKey(), 0 - entry.getValue());
                String firstPair = match.charAt(0) + insert;
                String secondPair = insert + match.charAt(1);

                newMap.put(firstPair, newMap.getOrDefault(firstPair, 0L) + entry.getValue());
                newMap.put(secondPair, newMap.getOrDefault(secondPair, 0L) + entry.getValue());
                charFreq.put(insert.charAt(0), charFreq.getOrDefault(insert.charAt(0), 0L) + entry.getValue());

            }
        }

        return newMap;
    }

}