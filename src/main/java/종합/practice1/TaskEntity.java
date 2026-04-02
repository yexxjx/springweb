package example.practice1;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "Task")
@NoArgsConstructor @AllArgsConstructor @Builder @Data
public class TaskEntity extends TaskBaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String requester;
    private String status;
}
