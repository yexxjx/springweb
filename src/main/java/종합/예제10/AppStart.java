package 종합.예제10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // JPA 감사 기능 활성화 (BaseTime 적용하려고)
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
}
