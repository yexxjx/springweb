package 종합.예제10.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import 종합.예제10.board.entity.CommentEntity;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class CommentDto {
    private Integer cno;
    private String cwriter;
    private String ccontent;

    private String createDate;
    private String updateDate;

    public CommentEntity toEntity(){
        return CommentEntity.builder()
                .cno(cno)
                .cwriter(cwriter)
                .ccontent(ccontent)
                .build();
    }
}
