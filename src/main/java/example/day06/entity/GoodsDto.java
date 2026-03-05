package example.day06.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class GoodsDto {
    private Integer gno;
    private String gname;
    private Integer gprice;
    private String gdesc;

    // + baseTime
    private String createDate;
    private String updateDate;

    // *** DTO > ENTITY 변환함수
    public GoodsEntity toEntity(){
        // 빌더패턴이란? new 생성자가 아닌 함수로 객체 생성
        // this란? 해당 메소드/함수 실행한 객체
        return GoodsEntity.builder() // 빌더 시작
                .gno(gno)
                .gname(gname)
                .gprice(gprice)
                .gdesc(gdesc)
                .build();
    }
}
