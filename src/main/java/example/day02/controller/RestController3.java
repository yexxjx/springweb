package example.day02.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @Component 빈 등록
// @Controller +HTTP 통신 기능 포함 = 사용처: view(화면) 반환
@RestController // +ResponseBody 포함 = 사용처: 값(JSON) 반환
@RequestMapping("/day02") // @RequestMapping("/공통URL") 해당 컨트롤러 내 메소드들이 사용하는 공통 URL 정의
public class RestController3 {
    //1. 클래스가 @RestController 이므로 @ResponseBody 생략해도 된다
    @GetMapping("/task6") // * 클래스가 @RequestMapping("/공통URL") 가지므로 localhost:8080/day02/task6
    public String method1(){return "서버에게 받은 메세지";}
}
