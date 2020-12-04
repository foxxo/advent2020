package DayFour;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayFour {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/input4.txt");


        BufferedReader reader = Files.newBufferedReader(path);
        Stream<String> lines = reader.lines();
        List<String> inputLines = lines.collect(Collectors.toList());

        List<Passport> passports = extractPassports(inputLines);

        List<String> requiredFields = Arrays.asList("ecl", "pid", "eyr", "hgt", "byr", "ecl", "hcl", "iyr");

        int numValid = 0;

        for(Passport p : passports)
        {
            if(p.isValid(requiredFields)) {
                System.out.println("Found this passport VALID: \n" + p.toString());
                numValid++;
            }
        }

        System.out.println("Valid passports: " + numValid);
    }

    private static ArrayList<Passport> extractPassports(List<String> input)
    {
        int i = 0;
        ArrayList<Passport> passports = new ArrayList();
        while(i < input.size())
        {
           String passportString = "";
           while(i < input.size() && !input.get(i).isEmpty())
           {
               passportString += input.get(i) + " ";
               i++;
           }
           if(!passportString.isEmpty())
               passports.add(new Passport(passportString));
           else
               i++;

        }
        return passports;
    }
}