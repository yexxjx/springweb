package testfile.employeemanager;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    // 사원 등록
    @PostMapping
    public ResponseEntity<?> create(EmployeeDto employeeDto){
        return  ResponseEntity.ok(employeeService.create(employeeDto));
    }

    // 사원 조회
    @GetMapping
    public ResponseEntity<?> read(){
        return ResponseEntity.ok(employeeService.read());
    }

    // 사원 수정
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam Integer eno, @RequestBody EmployeeDto employeeDto){
        return ResponseEntity.ok(employeeService.update(eno, employeeDto));
    }

    // 사원 삭제
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Integer eno){
        employeeService.delete(eno);
        return ResponseEntity.ok(true);
    }
}
