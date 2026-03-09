package example.day07.practice7.entity;

import example.day07.practice7.Basetime;
import example.day07.연관관계.CategoryEntity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Builder @Data
@Entity @Table(name = "student")
public class StudentEntity extends Basetime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    private String studentName;

    @ManyToOne
    @JoinColumn(name = "stdentId")
    private CategoryEntity studentEntity;

}
