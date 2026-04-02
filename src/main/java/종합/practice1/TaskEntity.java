package 종합.practice1;

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
    private Long id;
    private String title;
    private String content;
    private String requester;
    private String status;

    public TaskDto toDto(){
        return TaskDto.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .requester(this.requester)
                .status(this.status)
                .createDate(getCreateDate().toString())
                .updateDate(getUpdateDate().toString())
                .build();
    }
}
