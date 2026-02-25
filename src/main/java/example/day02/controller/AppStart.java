package example.day02.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @ComponentScan: 스프링이 실행할 때 스프링 컨테이너에 등록할 빈(@Component)을 동일/하위 패키지 찾아서 등록
// @Component 후손들: @Controller @Service @RestController @Repository 등 몇몇 아노테이션들은 내장됨
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
}
