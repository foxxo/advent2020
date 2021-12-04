package TwentyOne.DayFour;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DayFour {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("2021/input4");

       List<Integer> drawnNumbers = Arrays.stream(inputLines.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());

       List<BingoBoard> boards = new ArrayList();

        for(int i = 2; i < inputLines.size(); i++)
        {
            BingoBoard b = new BingoBoard(5);
            int row = 0;
            while(i < inputLines.size() && !inputLines.get(i).equals(""))
            {
                b.setRow(row, Arrays.stream(inputLines.get(i).split("\\s+")).filter(s -> !s.isEmpty()).map(Integer::parseInt).collect(Collectors.toList()));
                row++;
                i++;
            }
            boards.add(b);
        }
        BingoBoard winner = null;
        int lastNumber = -1;
        for(int n : drawnNumbers)
        {
//            if(winner != null)
//                break;
            System.out.println("Next number is " + n);
            for(BingoBoard b : boards)
            {
                if(b.done)
                    continue;
                b.light(n);
                System.out.println("Checking board \n" + b);
                if(b.check()) {
                    System.out.println("BINGO!");
                    winner = b;
                    lastNumber = n;
                    b.done = true;

                }
            }
        }
        System.out.println("The winning board is\n" + winner);
        System.out.println("The final number called was " + lastNumber);
        System.out.println("Score: " + winner.sumUnlit() * lastNumber);
    }
}
