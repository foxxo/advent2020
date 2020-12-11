package DayEleven;

import AdventUtil.AdventUtil;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.util.List;

public class DayEleven {
    public static void main(String[] args) throws IOException {

        List<String> inputLines = AdventUtil.readInputLines("input11");

        Map seatMap = new Map(inputLines);

        seatMap.print();

        int seatCount = processTillStable(seatMap);

        System.out.println("Seats occupied once stable: " + seatCount);




    }

    static int processTillStable(Map startMap)
    {
        Map nextMap = new Map(0,0);
        int iterations = 0;

        while(!startMap.isSame(nextMap))
        {
            if(iterations > 0)
                startMap = nextMap.copy();
            nextMap = startMap.copy();
            processSeating(startMap, nextMap);
            System.out.println("");
            nextMap.print();
            iterations++;
        }

        return nextMap.countFilledSeats();
    }

    static void processSeating(Map startMap, Map destMap)
    {
        for(int y = 0; y < startMap.height; y++)
            for(int x = 0; x < startMap.width; x++) {
                destMap.grid[x][y].content = startMap.grid[x][y].runRules();
            }
    }

}

