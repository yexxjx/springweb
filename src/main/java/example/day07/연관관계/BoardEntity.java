package example.day07.연관관계;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity @Table(name="board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bno;
    private String bcontent;

    // ** 단방향 ** FK 만들기 **
    // @JoinColumn: 관례적으로 FK 필드명도 PK 필드명과 동일
    // @MantToOne: 다수가 하나에게, M:1, 여러 개 게시물이 하나의 카테고리를 참조
    @ManyToOne
    @JoinColumn(name = "cno")
    private CategoryEntity categoryEntity;

    // ** 양방향 **
    @OneToMany(mappedBy = "boardEntity")
    @ToString.Exclude @Builder.Default
    private List<ReplyEntity> replyEntityList=new ArrayList<>();
}
