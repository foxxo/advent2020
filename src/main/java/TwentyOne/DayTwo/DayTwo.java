package TwentyOne.DayTwo;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.List;

public class DayTwo {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = AdventUtil.readInputLines("2021/input2");

        Sub sub = new Sub();


        for(String s : inputLines)
        {
            sub.move(parseCommand(s, sub.aim));
        }

        System.out.println("Final Position: " + sub.pos.x + ", " + sub.pos.z + "\nProduct: " + sub.pos.x * sub.pos.z);

    }

    static Sub parseCommand(String cmd, int currAim)
    {
        int space = cmd.indexOf(" ");
        String dir = cmd.substring(0, space);
        int dist = Integer.parseInt(cmd.substring(space+1));

        Sub s = new Sub();

        switch(dir){
            case "down":
                s.aim = dist;
                break;
            case "up":
                s.aim = -dist;
                break;
            case "forward":
                s.pos.x = dist;
                s.pos.z = currAim * dist;
                break;
        }
        return s;
    }
}
