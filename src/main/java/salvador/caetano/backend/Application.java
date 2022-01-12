package salvador.caetano.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@PropertySource(name = "general-properties", value = { "classpath:path to your app.properties"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
