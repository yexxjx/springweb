package example.day07.practice7;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class EnrollEntity extends Basetime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enrollId;
    private String status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    private CourseEntity courseEntity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId")
    private StudentEntity studentEntity;

    @OneToMany(mappedBy = "courseEntity")
    @ToString.Exclude
    @Builder.Default
    private List<EnrollEntity> enrollEntityList=new ArrayList<>();

    @OneToMany(mappedBy = "studentEntity")
    @ToString.Exclude
    @Builder.Default
    private List<StudentEntity> studentEntityList=new ArrayList<>();
}
