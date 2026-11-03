import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.*;

public class FolderReport {

    public static void main(String[] args) throws IOException {

        Path root = Path.of(".");
        Path report = Path.of("folder-report.txt");

        try (BufferedWriter w = Files.newBufferedWriter(report)) {

            Files.walkFileTree(root, new SimpleFileVisitor<>() {

                @Override
                public FileVisitResult visitFile(
                        Path file,
                        BasicFileAttributes attrs
                ) throws IOException {

                    String line = file.getFileName()
                            + " | " + attrs.size()
                            + " bytes | " + attrs.lastModifiedTime();

                    w.write(line);
                    w.newLine();

                    return FileVisitResult.CONTINUE;
                }
            });
        }

        System.out.println(
                "Report written to " + report.toAbsolutePath()
        );
    }
}