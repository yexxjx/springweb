package testfile.employeemanager;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    // 사원 등록
    public EmployeeDto create(EmployeeDto employeeDto){
        return employeeRepository.save(employeeDto.toEntity()).toDto();
    }

    // 사원 조회
    public List<EmployeeDto> read(){
        return employeeRepository.findAll().stream().map(EmployeeEntity::toDto).collect(Collectors.toList());
    }

    // 사원 수정
    public EmployeeDto update(Integer eno, EmployeeDto employeeDto){
        EmployeeEntity employee=employeeRepository.findById(eno).orElseThrow();
        employee.setEname(employeeDto.getEname());
        employee.setErole(employeeDto.getErole());
        return employee.toDto();
    }

    // 사원 삭제
    public void delete(Integer eno){
        EmployeeEntity employee=employeeRepository.findById(eno).orElseThrow();
        employeeRepository.delete(employee);
    }
}
