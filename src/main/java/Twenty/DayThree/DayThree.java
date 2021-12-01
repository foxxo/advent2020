package Twenty.DayThree;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayThree {

    public static void main(String[] args) throws IOException
    {
        Path path = Paths.get("src/main/resources/2020/input3.txt");


        BufferedReader reader = Files.newBufferedReader(path);
        Stream<String> lines = reader.lines();
        List<String> inputLines = lines.collect(Collectors.toList());

        Map slopeMap = new Map(inputLines);

        ArrayList<Integer> trees = new ArrayList();

        trees.add(slopeMap.collisionsForPattern(1, 1));
        trees.add(slopeMap.collisionsForPattern(3, 1));
        trees.add(slopeMap.collisionsForPattern(5, 1));
        trees.add(slopeMap.collisionsForPattern(7, 1));
        trees.add(slopeMap.collisionsForPattern(1, 2));

        long product = 1;

        for(int i : trees)
        {
            product = product * i;
        }

        System.out.println("Trees encountered: " + trees);
        System.out.println("Multiplied: " + product);

    }
}
