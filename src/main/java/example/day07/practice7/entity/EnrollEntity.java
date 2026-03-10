package example.day07.practice7.entity;

import example.day07.practice7.Basetime;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity @Table(name = "enroll")
public class EnrollEntity extends Basetime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enrollId;
    private String status;

    // 단방향
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId") // 데이터베이스에서는 courseId와 조인 FK
    private CourseEntity courseEntity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="studentId") // FK
    private StudentEntity studentEntity;
}
