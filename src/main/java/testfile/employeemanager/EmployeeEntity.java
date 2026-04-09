package testfile.employeemanager;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import testfile.departmentmanager.DepartmentEntity;

@Entity
@NoArgsConstructor @AllArgsConstructor @Builder @Data
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eno;

    @Column(nullable = false)
    private String ename;

    @Column(nullable = false)
    private String erole;

    public EmployeeDto toDto(){
        return EmployeeDto.builder()
                .eno(eno)
                .ename(ename)
                .erole(erole)
                .build();
    }
}
