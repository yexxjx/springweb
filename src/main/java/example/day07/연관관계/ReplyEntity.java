package example.day07.연관관계;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity @Table(name="reply")
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rno;
    private String rcontent;
    // ** 단방향 ** FK(만들기)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // FK 제약조건 설정
    @JoinColumn(name = "bno") // FK 필드명 설정
    private BoardEntity boardEntity;
}

/*
    - 영속성이란? 자바 객체와 데이터베이스 데이터 간 매핑/연결 상태
        - 영속성 해제란? 자바 객체와 데이터베이스 데이터 간 매핑/연결 해제
    - cascade 속성이란? PK와 FK 제약조건 옵션
        CascadeType.ALL: 부모가 삭제/수정/저장되면 자식도 같이 삭제/수정/저장 반영됨
        CascadeType.REMOVE: 부모가 삭제되면 자식도 같이 삭제 반영
        CascadeType.MERGE: 부모가 수정되면 자식도 같이 수정 반영
        CascadeType.DETACH: 부모가 영속 해제 되면 자식도 같이 영속 해제 반영
        CascadeType.REFRESH: 부모가 재호출(갱신)되며 자식도 같이 재호출(갱신) 한다.
        CascadeType.PERSIST: 부모가 저장되면 자식도 같이 저장

     - fetch: 부모[PK] 조회시 자식[FK] 관계에서 엔티티 조회 여부 선택
        FetchType.EAGER: (기본값) 해당 엔티티 조회시 참조 엔티티 모두 즉시 조회한다.
            - 특징: 초기 로딩이 느림, 재사용성 빠름, 불필요한 정보까지 있을 경우 <성능 저하>
        FetchType.LAZY: 해당 엔티티 조회시 참조 엔티티는 조회 하지 않는다 <참조 엔티티 호출시 조회>
            - 특징: 초기 로딩 빠름, 재사용성 느림, 필요한 정보만 호출하기 때문에 적절하게 사용 <지연 로딩>
     - 단방향/양방향 활용
        - 만약에 1번 카테고리에 게시물 등록할 경우 ** FK 필드에 FK값이 아닌 FK 엔티티 대입한다**
          BoardEntity saveEntity=new BoardEntity();
          // saveEntity.setCatagory(1); [X]
          CategoryEntity category=repository.findById(1).get();
          saveEntity.setCategoryEntity(category); [O]
          repository.save(saveEntity);

        - 만약에 3번 게시물에 댓글 등록할 경우, ** FK 필드에 FK값만 3 대신에 3갖는 FK 엔티티 찾아서 대입한다 **
          ReplyEntity saveEntity=new ReplyEntity();
          BoardEntity board=repository.findById(3).get();
          saveEntity.setBoardEntity(board);
          repository.save(saveEntity);
 */
