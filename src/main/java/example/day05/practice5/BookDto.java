package example.day05.practice5;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class BookDto {
    private Integer bno;
    private String bid;
    private String bname;
    private String bwriter;
    private String bpublish;
}
