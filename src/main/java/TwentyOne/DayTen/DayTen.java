package TwentyOne.DayTen;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.*;


public class DayTen {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("2021/input10");

        Map<Character,Character> containers = Map.of(
                '{', '}',
                '(',')',
                '<','>',
                '[',']'
        );

        Map<Character,Integer> scores = Map.of(
                '}', 1197,
                ')', 3,
                ']', 57,
                '>', 25137,
                '(', 1,
                '[',2,
                '{',3,
                '<',4
        );


        Stack<Character> openBlocks = new Stack();
        Stack<Character> illegalClosers = new Stack();
        List<Long> incompleteLineScores = new ArrayList();

        for(String line : inputLines) {
            String text = line;
            boolean corrupted = false;
            while (!line.isEmpty()) {
                char nextChar = line.charAt(0);
                if (containers.containsKey(nextChar))
                    openBlocks.push(nextChar);
                else if (containers.containsValue(nextChar)) {
                    if (containers.get(openBlocks.lastElement()) == nextChar)
                        openBlocks.pop();
                    else {
//                        System.out.println("Line " + text + " is corrupted");
                        illegalClosers.push(nextChar);
                        corrupted = true;
                        break;
                    }
                }
                line = line.substring(1);
            }
            if(!openBlocks.isEmpty() && !corrupted)
            {
                long incompleteScore = 0;
                while(!openBlocks.isEmpty())
                {
                    incompleteScore *= 5;
                    incompleteScore += scores.get(openBlocks.pop());
                }
                incompleteLineScores.add(incompleteScore);
            }

            openBlocks.clear();
        }

        int score = 0;
        while(!illegalClosers.isEmpty())
        {
            score += scores.get(illegalClosers.pop());
        }

        System.out.println("Syntax score: " + score);

        Collections.sort(incompleteLineScores);

        System.out.println(incompleteLineScores);

        System.out.println("Completion score: " + incompleteLineScores.get(incompleteLineScores.size()/2));

    }
}