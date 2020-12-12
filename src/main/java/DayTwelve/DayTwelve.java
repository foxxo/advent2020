package DayTwelve;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.List;

public class DayTwelve {
    public static void main(String[] args) throws IOException {

        List<String> inputLines = AdventUtil.readInputLines("short12");
        Boat myBoat = new Boat(0, 0, 90);

        for(String l : inputLines) {
            myBoat.processOrder(new NavOrder(l));
            System.out.println("Boat has moved to " + myBoat.position +  ", heading " + myBoat.heading);
        }

    }
}
