package testfile.departmentmanager;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Builder @Data
public class DepartmentDto {
    private Long dno;
    private String dname;

    public DepartmentEntity toEntity(){
        return DepartmentEntity.builder()
                .dno(dno)
                .dname(dname)
                .build();
    }

}
