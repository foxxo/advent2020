package DayNineteen;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DayNineteen {


    static HashMap<String, Rule> rulesMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        List<String> inputLines = AdventUtil.readInputLines("short19");

        for(String i : inputLines)
        {
            if("".equals(i))
            {
                break;
            }

            String ruleIndex = i.substring(0, i.indexOf(":"));
            Rule r = parseRule(i.substring(i.indexOf(":")+2), ruleIndex);

            rulesMap.put(ruleIndex, r);
        }

        int validStringsCount = 0;
        for(String i : inputLines.subList(inputLines.indexOf("")+1, inputLines.size()))
        {
            if(rulesMap.get("0").eval(i)) {
                validStringsCount++;
            }
        }

        System.out.println("Total valid input strings: " + validStringsCount);
    }

    public static Rule parseRule(String inputLine, String index)
    {
        Rule myRule;
        inputLine =  inputLine.trim();
        if(inputLine.contains("|"))
        {
            myRule = new OrRule();
            List<String> ors = Arrays.asList(inputLine.split("[|]"));
            for(String s : ors)
            {

                Rule n = parseRule(s, index+"-"+s);
                myRule.subRules.add(n);

            }
        }
        else if(inputLine.contains("\""))
        {
            myRule = (rulesMap.containsKey(index) ? rulesMap.get(index) : new Rule());
            myRule.matchValue = inputLine.substring(1, inputLine.length()-1);
        }
        else if(inputLine.contains(" "))
        {
            myRule = new AndRule();
            List<String> ands = Arrays.asList(inputLine.split(" "));
            for(String s : ands)
            {
                Rule n = parseRule(s, s);
                myRule.subRules.add(n);


                if(!rulesMap.containsKey(s))
                    rulesMap.put(index, n);
            }
        }
        else
        {
            myRule = new Rule();

            if(!rulesMap.containsKey(inputLine))
                rulesMap.put(inputLine, new Rule());
            myRule.subRules.add(rulesMap.get(inputLine));
        }
        return myRule;
    }
}
