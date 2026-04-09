package testfile.employeemanager;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Builder @Data
public class EmployeeDto {
    private Long eno;
    private String ename;
    private String erole;

    public EmployeeEntity toEntity(){
        return EmployeeEntity.builder()
                .eno(eno)
                .ename(ename)
                .erole(erole)
                .build();
    }
}
