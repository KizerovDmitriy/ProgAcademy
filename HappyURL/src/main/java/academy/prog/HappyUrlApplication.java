package academy.prog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HappyUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(HappyUrlApplication.class, args);
    }

}
