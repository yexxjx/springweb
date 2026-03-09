package example.day07.practice7;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class StudentEntity extends Basetime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    private String studentName;

    @OneToMany(mappedBy = "enrollEntity")
    @ToString.Exclude
    @Builder.Default
    private List<StudentEntity> studentEntityList=new ArrayList<>();

}
