package pl.wlazly.marcin.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.wlazly.marcin.model.ApplicationException;
import pl.wlazly.marcin.model.Routes;

/**
 * Created by marcin on 18.12.16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileImporterTest {

    @Autowired
    FileImporter fileImporter;

    @Test
    public void shouldImportTestData() {
        fileImporter.filePath = "src/test/resources/test-data.txt";

        Routes routes = fileImporter.importRoutesFromFile();

        Assert.assertTrue(routes.isCompleted());
    }

    @Test(expected = ApplicationException.class)
    public void shouldThrowAnExceptionNoEnough() {
        fileImporter.filePath = "src/test/resources/no-enough-rows-test-data.txt";

        fileImporter.importRoutesFromFile();
    }

    @Test(expected = ApplicationException.class)
    public void shouldThrowAnExceptionTooMuch() {
        fileImporter.filePath = "src/test/resources/too-much-rows-test-data.txt";

        fileImporter.importRoutesFromFile();
    }

    @Test(expected = ApplicationException.class)
    public void shouldThrowAnExceptionNoUnique() {
        fileImporter.filePath = "src/test/resources/no-unique-routes-test-data.txt";

        fileImporter.importRoutesFromFile();
    }

    @Test(expected = ApplicationException.class)
    public void shouldThrowAnExceptionTwoSize() {
        fileImporter.filePath = "src/test/resources/two-size-declarations-test-data.txt";

        fileImporter.importRoutesFromFile();
    }

    @Test(expected = ApplicationException.class)
    public void shouldThrowAnExceptionNoFile() {
        fileImporter.filePath = "src/test/resources/no-existing-file.txt";

        fileImporter.importRoutesFromFile();
    }
}
