package IO;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReaderTest {

    @Test
    public void assertThatInputFileExists() {
        File file = new File(System.getProperty("user.dir") + "/src/test/java/resources/inputFile.dat");
        assertTrue(file.exists());
    }

    @Test
    public void assertThatReportNumberExists() {
        File file = new File(System.getProperty("user.dir") + "/src/test/java/resources/reportNumber.dat");
        assertTrue(file.exists());
    }

    @Test
    public void assertThatInputFileIsNotEmpty() {
        File file = new File(System.getProperty("user.dir") + "/src/test/java/resources/inputFile.dat");
        assertTrue(file.length() != 0);
    }

    @Test
    public void assertThatReportNumberIsNotEmpty() {
        File file = new File(System.getProperty("user.dir") + "/src/test/java/resources/reportNumber.dat");
        assertTrue(file.length() != 0);
    }

    @Test
    public void assertThatInputFileWasCompletelyRead() {
        String path = System.getProperty("user.dir") + "/src/test/java/resources/inputFile.dat";
        Reader reader = new Reader();
        ArrayList<String> fileContent = new ArrayList<>();
        fileContent = reader.getContentFromFile(path, fileContent);

        assertEquals(10, fileContent.size());
    }

    @Test
    public void assertThatReportNumberContentIsCorrect() {
        String path = System.getProperty("user.dir") + "/src/test/java/resources/reportNumber.dat";
        Reader reader = new Reader();
        String fileContentActual;

        fileContentActual = reader.getContentFromFile(path);

        assertEquals("1", fileContentActual);
    }

    @Test
    public void assertThatInputFileContentIsCorrect() {
        String path = System.getProperty("user.dir") + "/src/test/java/resources/inputFile.dat";
        Reader reader = new Reader();
        ArrayList<String> fileContentActual = new ArrayList<>();
        List<String> fileContentExpected;

        fileContentActual = reader.getContentFromFile(path, fileContentActual);
        fileContentExpected = Arrays.asList(
                "001ç1234567891234çPedroç50000",
                "001ç1234567891987çAmericaç50000",
                "001ç3245678865434çPauloç40000.99",
                "001ç6541567891234çJoãoç30000",
                "001ç6556557891234çMariaç20000",
                "002ç2345675434544345çJose da SilvaçRural",
                "002ç2345675433444345çEduardo PereiraçRural",
                "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro",
                "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo",
                "003ç09ç[1-20-500]çJoão"
        );

        assertEquals(fileContentExpected, fileContentActual);
    }
}