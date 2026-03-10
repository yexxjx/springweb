package example.day07.practice7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class EnrollController {
    @Autowired
    private EnrollService enrollService;

    @PostMapping("/course") // Map 타입이란? key와 value 한쌍(entry)으로 여러 entry 저장함 == JSON/DTO
    public boolean 과정등록(@RequestBody Map<String, Object> map){
        boolean result= enrollService.과정등록(map);
        return result;
    }
    @PostMapping("/student")
    public boolean 학생등록(@RequestBody Map<String, Object> map){
        boolean result= enrollService.학생등록(map);
        return result;
    }

    @PostMapping("/enroll")
    public boolean 수강등록(@RequestBody Map<String, Object> map){
        boolean result=enrollService.수강등록(map);
        return result;
    }

    @GetMapping("/get")
    public Map<String, Object> 개별조회(@RequestParam int enrollId){
        return enrollService.개별조회(enrollId);
    }

}
