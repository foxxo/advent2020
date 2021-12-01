package Twenty.DayTwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayTwo {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/2020/input2.txt");


        BufferedReader reader = Files.newBufferedReader(path);
        Stream<String> lines = reader.lines();
        List<String> inputLines = lines.collect(Collectors.toList());

        int numValidPasswords1 = 0;

        for(String line : inputLines)
        {
            PasswordRule rule = new PasswordRule(line);
            String password = line.substring(line.lastIndexOf(" ")+1);
            if(rule.passwordIsValid(password))
                numValidPasswords1++;

        }
        int numValidPasswords2 = 0;

        for(String line : inputLines)
        {
            PasswordRule rule = new PasswordRule(line);
            String password = line.substring(line.lastIndexOf(" ")+1);
            if(rule.passwordIsValidByPlacement(password))
                numValidPasswords2++;

        }

        System.out.println("Number of valid passwords by rule 1: " + numValidPasswords1);
        System.out.println("Number of valid passwords by rule 2: " + numValidPasswords2);
    }



}
