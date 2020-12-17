package DaySeventeen;

import AdventUtil.AdventUtil;
import java.io.IOException;
import java.util.List;

public class DaySeventeen {

    public static void main(String[] args) throws IOException {

        List<String> inputLines = AdventUtil.readInputLines("input17");

        Map powerGrid = new Map(inputLines);
        powerGrid.print();

        for(int i = 0; i < 6; i++)
        {
            powerGrid = processGridRules(powerGrid);
            powerGrid.print();
        }

        System.out.println("Active cubes: " + powerGrid.countActiveCubes());

    }

    static Map processGridRules(Map startMap) {
        startMap = startMap.expandBoundaries();
        Map destMap = startMap.copy();
        System.out.println("Expanded Map");
        destMap.print();

        for (int y = 0; y < destMap.height; y++)
            for (int x = 0; x < destMap.width; x++)
                for (int z = 0; z < destMap.depth; z++)
                    destMap.grid[x][y][z].content = startMap.get(x, y, z).runRules();

        return destMap;
    }
}
