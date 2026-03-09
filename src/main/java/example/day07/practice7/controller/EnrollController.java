package example.day07.practice7.controller;

import example.day07.practice7.dto.EnrollDto;
import example.day07.practice7.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EnrollController {
    @Autowired
    private EnrollService enrollService;

    @PostMapping("/practice7/enroll")
    public boolean 수강등록(@RequestParam EnrollDto enrollDto){
        boolean result=enrollService.수강등록(enrollDto);
        return result;
    }

    @GetMapping("/practice7/enroll")
    public List<EnrollDto> 수강조회(){
        List<EnrollDto> result=enrollService.수강조회();
        return result;
    }
}
