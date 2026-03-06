package 종합.예제10.board.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import 종합.예제10.board.dto.BoardDto;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity @Table(name = "board")
public class BoardEntity extends BaseTime {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer bno;

    @Column (nullable = false, length = 255) // not null
    private String btitle;

    @Column (columnDefinition = "longtext not null") // 직접 SQL 설정 columnDefinition
    private String bcontent;

    @Column (length = 30, nullable = false)
    private String bwriter;

    // entity > dto
    public BoardDto toDto(){
        return BoardDto.builder()
                .bno(bno)
                .btitle(btitle)
                .bcontent(bcontent)
                .bwriter(bwriter)
                .createDate(getCreateDate().toString())
                .updateDate(getUpdateDate().toString())
                .build();

    }
}
