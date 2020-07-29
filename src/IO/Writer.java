package IO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Writer {

    public Writer () {

    }

    public void writeReport(String reportNumber, ArrayList<String> report, int customersSize, int salesPeopleSize,
                            String biggestSaleId, String lowestRankingSalespersonName) {
        report.add("*** Report " + reportNumber + " ***");

        for (int reportIndicator = 0; reportIndicator < 4; reportIndicator++) {
            switch (reportIndicator) {
                case 0:
                    report.add("Number of customers: " + customersSize);
                    break;
                case 1:
                    report.add("Number of salespeople: " + salesPeopleSize);
                    break;
                case 2:
                    report.add("Biggest sale ID: " + biggestSaleId);
                    break;
                case 3:
                    report.add("Salesperson with lowest ranking sale: " + lowestRankingSalespersonName);
            }
        }

        report.add("*** End report ***");
        report.add("\n");

        Path reportDestination = Paths.get(
                System.getProperty("user.home") + "/data/out/report_" + reportNumber + ".done.dat"
        );

        try {
            String reportInOneString = String.join("\n", report);
            Files.write(reportDestination, reportInOneString.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeReportNumber(String reportNumber, String reportNumberPath) {
        Path reportNumberDestination = Paths.get(reportNumberPath);

        try {
            byte[] stringToBytes = reportNumber.getBytes();
            Files.write(reportNumberDestination, stringToBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
