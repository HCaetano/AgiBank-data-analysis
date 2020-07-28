import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();

        try {
            File file = new File(System.getProperty("user.home") + "/data/in/file1.dat");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        Path out = Paths.get(System.getProperty("user.home") + "/data/out/file_out.dat");

        try {
            Files.write(out, lines, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

