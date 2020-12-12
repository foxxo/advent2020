package DayTwelve;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.List;

public class DayTwelve {
    public static void main(String[] args) throws IOException {

        List<String> inputLines = AdventUtil.readInputLines("input12");
        Boat myBoat = new Boat(0, 0, 90);

//        for(String l : inputLines) {
//            myBoat.processAbsoluteOrder(new NavOrder(l));
//            System.out.println("Boat has moved to " + myBoat.position +  ", heading " + myBoat.heading + " [" + myBoat.direction + "]");
//        }

        for(String l : inputLines)
        {
            myBoat.processRelativeOrder(new NavOrder(l));
            System.out.println("Boat has moved to " + myBoat.position +  ", nav waypoint at " + "[" + myBoat.waypoint + "]");
        }
        System.out.println("Manhattan distance from origin: " + (Math.abs(myBoat.position.x) + Math.abs(myBoat.position.y)));
    }
}
