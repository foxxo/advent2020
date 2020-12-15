package DayFifteen;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class DayFifteen {
    public static void main(String[] args) throws IOException {

        List<String> inputLines = AdventUtil.readInputLines("short15");
        HashMap<Integer, Stack<Integer>> numberAges = new HashMap<>();

        List<Integer> startingNumbers = Arrays.asList(inputLines.get(0).split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());

        int turn = 0;
        int numberSpoken = -1;
        while(turn < 30000000)
        {
            turn++;
            if(turn <= startingNumbers.size()) {
                numberSpoken = startingNumbers.get(turn-1);
            }
            else
            {
                if(numberAges.get(numberSpoken).isEmpty())
                    numberSpoken = turn;
                else
                    if(numberAges.get(numberSpoken).size()==1)
                        numberSpoken = 0;
                    else
                        numberSpoken = numberAges.get(numberSpoken).peek() - numberAges.get(numberSpoken).get(numberAges.get(numberSpoken).size()-2);
            }

            if(!numberAges.containsKey(numberSpoken))
                numberAges.put(numberSpoken, new Stack<>());
            numberAges.get(numberSpoken).push(turn);

        }

        System.out.println("Number spoken on turn " + turn + ": " + numberSpoken);
    }


}
