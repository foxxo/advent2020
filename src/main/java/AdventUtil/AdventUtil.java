package AdventUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdventUtil {

    public static List<String> readInputLines(String fileName) throws IOException {
        Path path = Paths.get("src/main/resources/" + fileName + ".txt");

        BufferedReader reader = Files.newBufferedReader(path);
        Stream<String> lines = reader.lines();
        return lines.collect(Collectors.toList());
    }
}
