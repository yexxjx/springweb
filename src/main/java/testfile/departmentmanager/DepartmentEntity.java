package testfile.departmentmanager;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor @AllArgsConstructor @Builder @Data
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dno;

    @Column(nullable = false, length = 10)
    private String dname;

    public DepartmentDto toDto(){
        return DepartmentDto.builder()
                .dno(dno)
                .dname(dname)
                .build();
    }

}
