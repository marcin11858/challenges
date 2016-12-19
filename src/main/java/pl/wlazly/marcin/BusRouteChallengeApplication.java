package pl.wlazly.marcin;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import pl.wlazly.marcin.service.RouteService;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableCaching
public class BusRouteChallengeApplication {

	private final static Logger logger = Logger.getLogger(BusRouteChallengeApplication.class);

	public static void main(String[] args) {
		logger.info("Application is starting");
		SpringApplication.run(BusRouteChallengeApplication.class, args);
	}
}
