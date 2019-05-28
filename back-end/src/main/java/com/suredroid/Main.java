import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	public static boolean debug = false;
	public static void main(String[] args) {
		if(args.length > 0 && args[0].equals("debug"))
			debug = true;
		SpringApplication.run(Main.class, args);
	}

}
