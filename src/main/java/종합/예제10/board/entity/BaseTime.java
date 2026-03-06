package 종합.예제10.board.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // 해당 클래스는 상속할 때 엔티티 매핑 포함
@Getter // 롬북: 상속받은 클래스가 멤버 변수 접근 허용
@EntityListeners(AuditingEntityListener.class) // 해당 클래스 JPA 감지 설정
public class BaseTime {
    @CreatedDate
    private LocalDateTime createDate; // 생성날짜/시간
    @LastModifiedDate
    private LocalDateTime updateDate; // 수정날짜/시간
}
