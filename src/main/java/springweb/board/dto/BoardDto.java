package springweb.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springweb.board.entity.BoardEntity;

@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class BoardDto {
    private Long bno;
    private String btitle;
    private String bcontent;
    private String bfile;

    // + DTO에는 엔티티 정보를 포함하지 않고 필요한 정보만 멤버변수 구성한다.
    private Long mno;
    private String mname; // name 대신 id 해도 됨
    // + BaseTime 멤버 변수
    private String createDate;
    private String updateDate;

    // + toEntity
    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .btitle(btitle).bcontent(bcontent).bfile(bfile)
                // .memberEntity() FK는 서비스에서 대입
                .build();
    }
}
