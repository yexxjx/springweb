package 종합.practice1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Builder @Data
public class TaskDto {
    private Long id;
    private String title;
    private String content;
    private String requester;
    private String status;
    private String createDate;
    private String updateDate;

    public TaskEntity toEntity(){
        return TaskEntity.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .requester(this.requester)
                .status(this.status)
                .build();
    }
}
