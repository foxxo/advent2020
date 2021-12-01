package Twenty.DayThirteen;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DayThirteen {
    public static void main(String[] args) throws IOException {

        List<String> inputLines = AdventUtil.readInputLines("2020/input13");
//        partOne(inputLines.get(1), currentTime);
        partTwo(inputLines.get(1));

    }

    private static void partTwo(String input)
    {
        long currentTime = 0;
        List<String> buses = Arrays.asList(input.split(","));

        long minBus = Long.parseLong(buses.get(0));
        int n = 2;
        do {
            while (!matchFound(currentTime, buses.subList(0, n))) {

                currentTime += minBus;

            }
            minBus =  buses.subList(0, n).stream().filter(b -> !b.equals("x")).map(Long::parseLong).reduce((long) 1, (a, b) -> a * b);
            n++;
        }while(n <= buses.size());
        System.out.println("Earliest matching timestamp: " + currentTime);
    }

    private static boolean matchFound(long time, List<String> buses)
    {
        for(String b : buses)
        {
            if(b.equals("x")) {
                time++;
                continue;
            }
            int interval = Integer.parseInt(b);
            if(time % interval != 0)
                return false;
            time++;
        }
        return true;
    }

    private static void partOne(String input) {

        List<String> buses = Arrays.asList(input.split(",")).stream().filter(b -> !b.equals("x")).collect(Collectors.toList());
        int currentTime = Integer.parseInt(buses.get(0));
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

