package 종합.practice1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins="http://localhost:5173") // 서로 다른 port(프로그램식별번호)간의 통신 허용
// SOP 정책으로 서로 다른 도메인은 통신이 불가능하다. HTTP 보안 정책
// CORS: 교차 출처 리소스 공유, 즉) 서로 다른 도메인(8080스프링,5173리액트) 통신 공유 허용
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;

    // 등록
    @PostMapping
    public ResponseEntity<?> create(@RequestBody TaskDto taskDto){
        return ResponseEntity.ok(taskService.create(taskDto));
    }

    // 목록 조회
    @GetMapping
    public ResponseEntity<?> read() {
        return ResponseEntity.ok(taskService.read());
    }

    // 상세 조회
    @GetMapping("/detail")
    public ResponseEntity<?> detail(@RequestParam Long id){
        return ResponseEntity.ok(taskService.detail(id));
    }

    // 수정
    @PutMapping
    public ResponseEntity<?> update(@RequestParam Long id, @RequestBody TaskDto request){
        return ResponseEntity.ok(taskService.update(id, request));
    }

    // 삭제
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id){
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
