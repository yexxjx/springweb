package example.day07.practice7.entity;

import example.day07.practice7.Basetime;
import example.day07.연관관계.CategoryEntity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity @Table(name = "course")
public class CourseEntity extends Basetime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    private String courseName;

}
