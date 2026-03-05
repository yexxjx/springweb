package example.day03.practice3;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @PostMapping
    public boolean 출석등록(@RequestBody AttendanceDto attendanceDto){
        System.out.println("AttendanceController.출석등록");
        System.out.println("attendanceDto = " + attendanceDto);
        return true;
    }

    @GetMapping
    public List<AttendanceDto> 출석전체조회(){
        List<AttendanceDto> list=new ArrayList<>();
        list.add(new AttendanceDto(1, "이연지", "2026-03-05", "출석"));
        list.add(AttendanceDto.builder().status("출석").studentName("이연지").date("206-03-05").ano(1).build());
        return list;
    }
}
