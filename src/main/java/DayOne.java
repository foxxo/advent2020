import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayOne {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/input.txt");


        BufferedReader reader = Files.newBufferedReader(path);
        Stream<String> lines = reader.lines();
        List<Integer> sourceNumbers = lines.map(Integer::parseInt).collect(Collectors.toList());

        List<Integer> solution = new ArrayList();

        for(int x = 0; x < sourceNumbers.size(); x++)
        {
            if(x == 22)
                System.out.print("");
            solution.add(sourceNumbers.get(x));
            if (solved(sourceNumbers, solution, 2020, 3, x+1))
                break;
            solution.remove(solution.size()-1);
        }

        System.out.println("Numbers: " + solution);
        System.out.println("Product: " + solution.stream().reduce(1, (a, b) -> a * b));
    }

    static boolean solved(List<Integer> sourceNumbers, List<Integer> solutionNums, int targetValue, int targetSize, int startIndex)
    {
        boolean solved;

        for(int i = startIndex; i < sourceNumbers.size(); i++)
        {
            solutionNums.add(sourceNumbers.get(i));
            //maximum depth reached
            if(solutionNums.size() == targetSize)
            {
                solved = (summarizeList(solutionNums) == targetValue);
                if(solved) return true;
                else
                    solutionNums.remove(solutionNums.size()-1);
            }
            //we have to go deeper
            else {
                solved = (solved(sourceNumbers, solutionNums, targetValue, targetSize, startIndex +1));
                if(!solved)
                    solutionNums.remove(solutionNums.size()-1);
                else
                    return true;
            }
        }
        return false;
    }

    static int summarizeList(List<Integer> nums)
    {
        return nums.stream().mapToInt(i -> i).sum();
    }
}
