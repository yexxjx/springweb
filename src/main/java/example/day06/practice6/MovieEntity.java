package example.day06.practice6;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity
@Table(name="movie")
public class MovieEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer mid;

    @Column(nullable = false, length = 50, unique = true, insertable = true, updatable = true)
    private String mtitle;

    private String mdirector;
    private String mrelease;
    private String mrating;

    public MovieDto toDto(){
        return MovieDto.builder()
                .mid(mid)
                .mtitle(mtitle)
                .mdirector(mdirector)
                .mrelease(mrelease)
                .mrating(mrating)
                .createDate(getCreateDate().toString())
                .updateDate(getUpdateDate().toString())
                .build();
    }
}
