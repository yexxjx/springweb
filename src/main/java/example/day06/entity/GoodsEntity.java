package example.day06.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity
@Table(name="goods") // 생략시 클래스명으로 자동 생성
public class GoodsEntity extends BaseTime {
    @Id // JPA는 엔티티내 1개 이상의 primary key 필수로 함
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer gno;

    @Column(name = "제품명", nullable = false, length = 100, unique = true, insertable = true, updatable = true)
    private String gname;

    // @Column 생략시 자바의 타입 > SQL 타입, 자바의 변수명 > SQL 필드명
    private Integer gprice;

    @Column(columnDefinition = "varchar(100) default '제품설명' not null")
    private String gdesc;

    // *** ENTITY > DTO  변환함수
    public GoodsDto toDto(){
        return GoodsDto.builder()
                .gno(gno)
                .gname(gname)
                .gprice(gprice)
                .gdesc(gdesc)
                .createDate(getCreateDate().toString())
                .updateDate(getUpdateDate().toString())
                .build();
    }
}

/*
@Id: primary key
@GeneratedValue(strategy=GenerationType.IDENTITY): auto_increment
@Column()
    name="필드명"             기본값 자바필드명
    nullable=false           기본값 true, not null
    length=길이               기본값 255, varchar(길이)
    unique=true              기본값 false, 중복여부
    insertable=true          기본값 true, insert할 때 적용여부
    updatable=true           기본값 true, update할 때 적용여부
    columnDefinition="SQL"   JPA가 아닌 네이티브(실제 SQL) 쿼리 작성
   레코드 생성(회원가입/등록일/주문일)날짜/수정날짜

 */
