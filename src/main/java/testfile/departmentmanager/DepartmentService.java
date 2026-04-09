package testfile.departmentmanager;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    // 부서 등록
    public DepartmentDto create(DepartmentDto departmentDto){
        return departmentRepository.save(departmentDto.toEntity()).toDto();
    }

    // 부서 전체 조회
    public List<DepartmentDto> read(){
        return departmentRepository.findAll().stream().map(DepartmentEntity::toDto).collect(Collectors.toList());
    }

    // 부서 수정
    public DepartmentDto update(Integer dno, DepartmentDto dname){
        DepartmentEntity departmentEntity=departmentRepository.findById(dno).orElseThrow();
        departmentEntity.setDname(dname.getDname());
        return departmentEntity.toDto();
    }

    // 부서 삭제
    public void delete(Integer dno){
        DepartmentEntity department=departmentRepository.findById(dno).orElseThrow();
        departmentRepository.delete(department);
    }
}
