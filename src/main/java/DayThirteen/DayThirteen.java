package DayThirteen;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DayThirteen {
    public static void main(String[] args) throws IOException {

        List<String> inputLines = AdventUtil.readInputLines("input13");
        int currentTime = Integer.parseInt(inputLines.get(0));
        List<String> buses = Arrays.asList(inputLines.get(1).split(",")).stream().filter(b -> !b.equals("x")).collect(Collectors.toList());
        int startTime = currentTime;
        boolean done = false;
        do {
            for(String b : buses)
            {
                int busTime = Integer.parseInt(b);
                if(currentTime % busTime == 0) {
                    System.out.println("Bus " + b + " departs at " + currentTime + " (product = " + busTime * (currentTime - startTime) + ")");
                    done = true;
                    break;
                }
            }
            currentTime++;
        }while(!done);

    }
}

