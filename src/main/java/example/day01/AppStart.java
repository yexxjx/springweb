package example.day01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class); // 주의: SpringBootApplication 아님 SpringApplication임
    }
}
