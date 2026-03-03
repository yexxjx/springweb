package example.day04.ch3;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED) // 빈생성자이면서 protected 접근제한
@AllArgsConstructor // 전체 매개변수를 갖는 생성자
@Getter // getter(자동생성) 메소드
@Entity // 데이터베이스의 테이블 레코드와 매핑(연결) 기술: ORM(Object Relational Mapping) 자바객체<->DB레코드
public class Member {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "id", updatable = false) // 필드/속성 설정, 수정 불가능
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}