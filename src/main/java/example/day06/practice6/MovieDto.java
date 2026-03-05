package example.day06.practice6;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class MovieDto {
    private Integer mid;
    private String mtitle;
    private String mdirector;
    private String mrelease;
    private String mrating;

    private String createDate;
    private String updateDate;

    public MovieEntity toEntity(){
        return MovieEntity.builder()
                .mid(mid)
                .mtitle(mtitle)
                .mdirector(mdirector)
                .mrelease(mrelease)
                .mrating(mrating)
                .build();
    }
}
