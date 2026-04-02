package example.practice1;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public boolean upload(@RequestBody TaskEntity task){

    }


    // 등록
    // 목록 조회
    // 상세 조회
    // 수정
    // 삭제
}
