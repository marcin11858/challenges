package pl.wlazly.marcin.service;

import org.apache.log4j.Logger;
import org.assertj.core.util.VisibleForTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.wlazly.marcin.model.ApplicationException;
import pl.wlazly.marcin.model.Routes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by marcin on 18.12.16.
 */
@Service
public class FileImporter {

    @VisibleForTesting
    String filePath;

    private static final String DELIMITER = " ";

    private final static Logger logger = Logger.getLogger(FileImporter.class);


    @Autowired
    public FileImporter(@Value("${bus.routes.challenge.file.path}" ) String filePath) {
        this.filePath = filePath;
    }

    public Routes importRoutesFromFile() {
        RoutesFactory routesFactory = new RoutesFactory();
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            logger.info("Start processing file: " + filePath);
            stream.forEach(r -> {
                        List<Integer> routeLine = Arrays.stream(r.split(DELIMITER))
                                .map(String::trim)
                                .mapToInt(Integer::parseInt)
                                .mapToObj(i -> i)
                                .collect(Collectors.toList());
                        if (routeLine.size() >= 3) {
                            routesFactory.processRout(routeLine.get(0), routeLine.subList(1, routeLine.size()));
                        } else if (routeLine.size() == 1) {
                            routesFactory.setTotalRoutes(routeLine.get(0));
                        }
                    }
            );
            logger.info("Processing file completed." );
        } catch (IOException e) {
            logger.error("Error during opening file, stacktrace: " + e);
            throw new ApplicationException("Error during opening file.");
        }
        Routes routes = routesFactory.build();
        if (!routes.isCompleted()) {
            throw new ApplicationException("Wrong data in file");
        }
        return routes;
    }

}
