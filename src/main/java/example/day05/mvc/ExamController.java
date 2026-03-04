package example.day05.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExamController {
    @Autowired
    private ExamService examService;

    // C insert
    @PostMapping("/day05/exam")
    public boolean 저장(@RequestBody ExamDto examDto){
        boolean result=examService.저장(examDto);
        return result;
    }
    // R select
    @GetMapping("/day05/exam")
    public List<ExamDto> 전체조회(){
        List<ExamDto> result=examService.전체조회();
        return result;
    }
    // U update
    @PutMapping("/day05/exam")
    public boolean 수정(@RequestBody ExamDto examDto){
        boolean result=examService.수정(examDto);
        return result;
    }

    // D delete
    @DeleteMapping("/day05/exam")
    public boolean 삭제(@RequestParam int eno){
        boolean result=examService.삭제(eno);
        return result;
    }
}
