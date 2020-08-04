package IO;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

    public Reader() {

    }

    public ArrayList<String> getContentFromFile(String pathName, ArrayList<String> fileContents) {
        File inputFile;

        try {
            inputFile = new File(pathName);
            Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (StringUtils.countMatches(line, "ç") < 3) {
                    line = line + scanner.nextLine();
                }

                fileContents.add(line);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return fileContents;
    }

    public String getContentFromFile(String pathName) {
        File reportNumberFile;
        String reportNumber = "";

        try {
            reportNumberFile = new File(pathName);
            Scanner scanner = new Scanner(reportNumberFile);

            while (scanner.hasNextLine()) {
                reportNumber = scanner.nextLine();
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return reportNumber;
    }
}
