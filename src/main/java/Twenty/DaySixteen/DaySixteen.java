package Twenty.DaySixteen;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;


public class DaySixteen {
    public static void main(String[] args) throws IOException {

        List<String> inputLines = AdventUtil.readInputLines("2020/input16");

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

        List<List<Integer>> validTickets = new ArrayList<>();
        List<Integer> invalidFieldValues = new ArrayList<>();
        HashMap<Rule, List<Integer>> rulesAndFailingFields = new HashMap<>();

        for (int t = 0; t < tickets.size(); t++) {
            int violations = 0;
            int badFields = 0;
            for (int i : tickets.get(t)) {
                boolean foundValidFieldForValue = false;
                for (Rule r : rules) {
                    if (r.checkValue(i)) {
                        violations = 0;
                        foundValidFieldForValue = true;
                    } else if (!foundValidFieldForValue){
                        //ticket t is invalid due to value i violating rule r
                        violations++;
                        badFields++;
                    }
                }
                if(violations != 0)
                    invalidFieldValues.add(i);

            }
            if(badFields < myTicket.size())
                validTickets.add(tickets.get(t));
        }

        System.out.println("Invalid numbers: " + invalidFieldValues);
        System.out.println("Error Rate: " + invalidFieldValues.stream().reduce(0, Integer::sum));

        for(int t = 0; t < validTickets.size(); t++) {
            for (int i = 0; i < myTicket.size(); i++){
                for (Rule r : rules) {
                    if(!rulesAndFailingFields.containsKey(r))
                        rulesAndFailingFields.put(r, new ArrayList<>());
                    if (!r.checkValue(validTickets.get(t).get(i))) {
                        //ticket t is invalid due to value i violating rule r
                        if(!rulesAndFailingFields.get(r).contains(i))
                            rulesAndFailingFields.get(r).add(i);
                    }
                }

            }
        }

        Map<Rule, List<Integer>> sortedMap
                = rulesAndFailingFields.entrySet()
                .stream()
                .sorted(comparingInt((Map.Entry<Rule, List<Integer>> e) -> e.getValue().size()).reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        HashMap<String, Integer> fieldsAndIndeces = new HashMap<>();

        sortedMap.forEach( (r, l) -> {
            for(int i = 0; i < myTicket.size(); i++) {
                if (!sortedMap.get(r).contains(i)) {
                    System.out.println("Assigned " + r.field + " to index " + i);
                    fieldsAndIndeces.put(r.field, i);
                    int indexTaken = i;
                    sortedMap.values().forEach(list -> list.add(indexTaken));
                }
            }
        });

        System.out.println("*******MY TICKET*******");
        System.out.println(printTicket(myTicket, fieldsAndIndeces));


    }

    private static String printTicket(List<Integer> ticket, HashMap<String, Integer> fieldsAndIndeces)
    {
        Map<String, Integer> sortedFieldsMap =
                fieldsAndIndeces.entrySet().stream()
                .sorted(comparingInt(Map.Entry::getValue)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        String outString = "";
        for (Map.Entry<String, Integer> entry : sortedFieldsMap.entrySet()) {
            String field = entry.getKey();
            Integer index = entry.getValue();
            outString = outString.concat(field + ": " + ticket.get(index) + "\n");

        }
        return outString;
    }

}
