package DayFive;

import AdventUtil.AdventUtil;
import java.io.IOException;
import java.util.List;

public class DayFive {
    static final int ROWS = 7;

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("input5");

        int max = 0;

        for(String s : inputLines)
        {
            int seat = (evaluateTicket(s));
            if(seat > max)
                max = seat;
        }

        System.out.println("Highest seat number: " + max);
    }

    static int evaluateTicket(String input)
    {
        int row = -1;
        int col = -1;

        row = binaryEval(input.substring(0, ROWS), 0, 127, 'F', 'B');
        col = binaryEval(input.substring(ROWS), 0, 7, 'L', 'R');

        int seatNum = 8 * row + col;
        System.out.println(String.format("Seat #%d, row %d, column %d", seatNum, row, col));
        return seatNum;
    }

    private static int binaryEval(String input, int min, int max, char firstHalfChar, char lastHalfChar) {
        int diff = max - min;
        if(diff == 0)
        {
            return max;
        }
        if(input.charAt(0) == firstHalfChar)
        {
            max -= diff / 2;
            max--;
        }
        else if(input.charAt(0) == lastHalfChar)
        {
            min += diff / 2;
            min++;
        }
        return binaryEval(input.substring(1), min, max, firstHalfChar, lastHalfChar);
    }
}
