package example.day07.practice7;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class CourseEntity extends Basetime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    private String courseName;

    @OneToMany(mappedBy = "courseEntity")
    @ToString.Exclude @Builder.Default
    private List<EnrollEntity> enrollEntityList=new ArrayList<>();
}
