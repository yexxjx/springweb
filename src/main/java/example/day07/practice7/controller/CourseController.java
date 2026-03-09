package example.day07.practice7.controller;

import example.day07.practice7.dto.CourseDto;
import example.day07.practice7.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/practice7/course")
    public boolean 과정등록(@RequestParam CourseDto courseDto){
        boolean result=courseService.과정등록(courseDto);
        return result;
    }
}
