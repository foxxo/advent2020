package DaySixteen;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DaySixteen {
    public static void main(String[] args) throws IOException {

        List<String> inputLines = AdventUtil.readInputLines("input16");

        List<Rule> rules = new ArrayList<>();
        for (String i : inputLines) {
            if (i.isEmpty())
                break;
            rules.add(new Rule(i));
        }

        List<Integer> myTicket = Arrays.stream(inputLines.get(inputLines.indexOf("your ticket:") + 1).split(",")).map(Integer::parseInt).collect(Collectors.toList());

        List<List<Integer>> tickets = new ArrayList<>();
        for (int i = inputLines.indexOf("nearby tickets:") + 1; i < inputLines.size(); i++) {
            tickets.add(Arrays.stream(inputLines.get(i).split(",")).map(Integer::parseInt).collect(Collectors.toList()));
        }

        List<Integer> validTicketIndices = new ArrayList<>();
        List<Integer> invalidFieldValues = new ArrayList<>();

        for (int t = 0; t < tickets.size(); t++) {
            int violations = 0;
            for (int i : tickets.get(t)) {
                boolean foundValid = false;
                for (Rule r : rules) {
                    if (r.checkValue(i)) {
                        System.out.println("Value " + i + " matches " + r);
                        violations = 0;
                        foundValid = true;
                    } else if (!foundValid){
                        //ticket t is invalid due to value i violating rule r
                        violations++;
                    }
                }
                if(violations != 0)
                    invalidFieldValues.add(i);

            }
            if (violations == 0)
                validTicketIndices.add(t);
        }

        System.out.println("Invalid numbers: " + invalidFieldValues);
        System.out.println("Error Rate: " + invalidFieldValues.stream().reduce(0, Integer::sum));
    }

}
