package example.day07.practice7.entity;

import example.day07.practice7.Basetime;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Builder @Data
@Entity @Table(name = "student")
public class StudentEntity extends Basetime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    private String studentName;

    @OneToMany(mappedBy = "studentEntity")
    @ToString.Exclude @Builder.Default
    private List<EnrollEntity> enrollEntityList=new ArrayList<>();
}
