package ca.mcgill.ecse321.GameOn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameOnApplication {
	public static boolean LoggedInAsCustomer = false;
	public static boolean LoggedInAsEmployee = false;
	public static boolean LoggedInAsAdmin = false;

	public static void main(String[] args) {
		SpringApplication.run(GameOnApplication.class, args);
	}

}
