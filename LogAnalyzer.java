import java.nio.file.*;
import java.io.IOException;
import java.util.*;

public class LogAnalyzer {

    public static void main(String[] args) throws IOException {

        Path dir = Path.of("sample-logs");

        Files.createDirectories(dir);

        Files.write(
                dir.resolve("app1.txt"),
                List.of("INFO started", "ERROR disk", "INFO ready")
        );

        Files.write(
                dir.resolve("app2.txt"),
                List.of("WARN memory", "ERROR network")
        );

        String keyword = "ERROR";

        int total = 0;
        int matches = 0;

        try (DirectoryStream<Path> files =
                Files.newDirectoryStream(dir, "*.txt")) {

            for (Path file : files) {

                List<String> lines = Files.readAllLines(file);

                total += lines.size();

                for (String line : lines) {
                    if (line.contains(keyword)) {
                        matches++;
                    }
                }

                System.out.println(
                        file + " size=" + Files.size(file) + " bytes"
                );
            }
        }

        System.out.println(
                "Total lines: " + total
                + ", lines containing " + keyword + ": " + matches
        );
    }
}