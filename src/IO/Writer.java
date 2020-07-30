package IO;

import Entity.Report;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Writer {

    public Writer() {

    }

    public void writeReport(String reportNumber, Report report) {
        Path reportDestination = Paths.get(
                System.getProperty("user.home") + "/data/out/report_" + reportNumber + ".done.dat"
        );

        try {
            String reportInOneString = String.join("\n", report.toString());
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
