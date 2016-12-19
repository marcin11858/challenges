package pl.wlazly.marcin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wlazly.marcin.model.Routes;
import pl.wlazly.marcin.service.FileImporter;

/**
 * Created by marcin on 18.12.16.
 */
@Configuration
public class BusRouteChallengeConfiguration {

    @Bean
    public Routes route(@Autowired FileImporter fileImporter) {
        return fileImporter.importRoutesFromFile();
    }
}
