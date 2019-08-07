package kr.co.crscube.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by crs32 on 2019-07-25.
 */

@SpringBootApplication
@ComponentScan("kr.co.crscube.crawler")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
