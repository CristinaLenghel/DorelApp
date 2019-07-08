package ro.sci.gr14;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the class that launches the application
 * Due to its annotations it functions as configuration class that declares
 * the @Bean methods and also triggers auto-configuration and component scanning
 *
 * @author Cristina Lenghel
 * @author Octavian Stefanescu
 * @author Marius-Laurentiu Lorincz
 * @author Cosmin Toma
 * @author Serban Moraru
 * @version 1.0
 * @since 2019-05-08
 */
@SpringBootApplication
public class DorelVineLaTineApplication {

	/**
	 * The main method of the application
	 *
	 * @param args String arguments that this Java application accepts when it is launched
	 */
	public static void main(String[] args) {
		SpringApplication.run(DorelVineLaTineApplication.class, args);
	}
}
