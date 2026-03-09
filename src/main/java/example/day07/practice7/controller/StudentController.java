package example.day07.practice7.controller;

import example.day07.practice7.dto.StudentDto;
import example.day07.practice7.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/practice7/student")
    public boolean 학생등록(@RequestParam StudentDto studentDto){
        boolean result=studentService.학생등록(studentDto);
        return result;
    }
}
