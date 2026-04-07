package springweb.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringExclude;
import springweb.board.dto.BoardDto;
import springweb.member.entity.BaseTime;
import springweb.member.entity.MemberEntity;

@Entity @Table(name = "board")
@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class BoardEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno; // 게시물 번호

    @Column(nullable = false, length = 255)
    private String btitle; // 게시물 제목

    @Column(nullable = false, columnDefinition = "longtext")
    private String bcontent; // 게시물 내용

    // 주로 첨부파일은 파일 자체를 저장하는 게 아니라 파일의 위치(서버 내 경로) 저장함
    // columnDefinition = "longtext" 도 가능
    @Column(nullable = true, length = 255)
    private String bfile; // 게시물 첨부파일, 만약에 게시물당 첨부파일 여러 개이면 엔티티 분리

    // + 단방향: 한 명의 회원이 여러 개 게시물 작성 1:N 관계
    @ManyToOne
    @JoinColumn(name = "mno")
    @ToStringExclude
    private MemberEntity memberEntity;

    // + toDto: 주로 조회할 때
    public BoardDto toDto(){
        return BoardDto.builder()
                .bno(bno).btitle(btitle).bcontent(bcontent).bfile(bfile)
                .mno(memberEntity.getMno()) // 작성자 FK의 회원번호
                .mname(memberEntity.getMname()) // 작성자 FK의 닉네임
                .createDate(getCreateDate().toString()) // 작성일
                .build();
    }
}
