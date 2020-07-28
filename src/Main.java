import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final ArrayList<String> fileContents = new ArrayList<>();
        final ArrayList<String> report = new ArrayList<>();
        Integer numberOfCustomers = 0;
        Integer numberOfSalespeople = 0;
        Integer biggestSaleId = 0;
        Integer lowestRankingSalesperson = 0;
        String reportNumberPath = System.getProperty("user.dir") + "/src/resources/reportNumber.dat";
        String reportNumber = "1";
        File data = null;
        
        try {
            data = new File(System.getProperty("user.home") + "/data/in/file1.dat");
            Scanner scanner = new Scanner(data);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                fileContents.add(line);
            }

            File reportNumberInfo = new File(reportNumberPath);
            scanner = new Scanner(reportNumberInfo);

            while (scanner.hasNextLine()) {
                reportNumber = scanner.nextLine();
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        for (String line : fileContents) {
            if (line.substring(0, 3).equals("001")) {
                numberOfSalespeople++;
            } else if (line.substring(0, 3).equals("002")) {
                numberOfCustomers++;
            }
        }

        Integer updatedReportNumber = Integer.parseInt(reportNumber);
        updatedReportNumber++;
        reportNumber = updatedReportNumber.toString();
        report.add("*** Report " + reportNumber + " ***");

        for (int reportIndicator = 0; reportIndicator < 4; reportIndicator++) {
            switch (reportIndicator) {
                case 1:
                    report.add("Number of clients: " + numberOfCustomers);
                case 2:
                    report.add("Number of salespeople: " + numberOfSalespeople);
                case 3:
                    report.add("Biggest sale ID: " + biggestSaleId);
                case 4:
                    report.add("Salesperson with lowest ranking sale : " + lowestRankingSalesperson);
            }
        }

        report.add("*** End report ***");
        report.add("\n");

        Path reportDestination = Paths.get(System.getProperty("user.home") + "/data/out/report.dat");
        Path reportNumberDestination = Paths.get(reportNumberPath);

        try {
            String reportNumberString = reportNumber;
            byte[] stringToBytes = reportNumberString.getBytes();

            Files.write(reportNumberDestination, stringToBytes);

            if (data.length() == 0) {
                Files.write(reportDestination, report, Charset.defaultCharset());
            } else {
                String reportInOneString = String.join("\n", report );
                Files.write(reportDestination, reportInOneString.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

