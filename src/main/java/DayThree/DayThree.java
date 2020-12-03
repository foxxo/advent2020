package DayThree;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayThree {

    public static void main(String[] args) throws IOException
    {
        Path path = Paths.get("src/main/resources/input3.txt");


        BufferedReader reader = Files.newBufferedReader(path);
        Stream<String> lines = reader.lines();
        List<String> inputLines = lines.collect(Collectors.toList());

        Map slopeMap = new Map(inputLines);
        slopeMap.print();

        Plot position = slopeMap.get(0,0);
        int trees = 0;

        while(position.y < inputLines.size()-1)
        {
            position = slopeMap.move(position, 3, 1);
            trees += (position.hasTree ? 1 : 0);
        }

        System.out.println("Trees encountered: " + trees);

    }
}
