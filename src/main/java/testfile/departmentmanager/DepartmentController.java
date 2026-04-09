package testfile.departmentmanager;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/depart")
@CrossOrigin(value = "http://localhost:5173")
public class DepartmentController {
    private final DepartmentService departmentService;

    // 부서 등록
    @PostMapping
    public ResponseEntity<?> create(@RequestBody DepartmentDto departmentDto){
        return ResponseEntity.ok(departmentService.create(departmentDto));
    }

    // 부서 조회
    @GetMapping
    public ResponseEntity<?> read(){
        return ResponseEntity.ok(departmentService.read());
    }

    // 부서 수정
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam Integer dno, @RequestBody DepartmentDto dname){
        return ResponseEntity.ok(departmentService.update(dno, dname));
    }

    // 부서 삭제
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Integer dno){
        departmentService.delete(dno);
        return ResponseEntity.ok(true);
    }
}
