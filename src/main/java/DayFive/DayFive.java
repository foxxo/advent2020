package DayFive;

import AdventUtil.AdventUtil;
import java.io.IOException;
import java.util.List;

public class DayFive {
    static final int ROWS = 127;
    static final int COLS = 7;
    static boolean[][] seatsTaken = new boolean[ROWS+1][COLS+1];

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

        for(int x = 1; x < ROWS-1; x++)
            for(int y = 1; y < COLS-1; y++)
            {
                if(!seatsTaken[x][y])
                    if(seatsTaken[x][y-1] && seatsTaken[x][y+1])
                    System.out.println("Found my seat! Row " + x + ", Col " + y + ", Seat #" + (8 * x + y));
            }
    }

    static int evaluateTicket(String input)
    {
        int row = -1;
        int col = -1;

        row = binaryEval(input.substring(0, COLS), 0, ROWS, 'F', 'B');
        col = binaryEval(input.substring(COLS), 0, COLS, 'L', 'R');

        int seatNum = 8 * row + col;
        seatsTaken[row][col] = true;
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
            max -= diff / 2 +1;
        }
        else if(input.charAt(0) == lastHalfChar)
        {
            min += diff / 2 +1;
        }
        return binaryEval(input.substring(1), min, max, firstHalfChar, lastHalfChar);
    }
}
