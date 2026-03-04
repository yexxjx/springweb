package example.day05.practice5;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Book")
@Data @NoArgsConstructor @AllArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bno;

    @Column(name="bname", length=255, nullable = true)
    private String bname;
    @Column(name="bid", length=255, nullable = true)
    private String bid;
    @Column(name="bwriter", length =255, nullable = true)
    private String bwriter;
    @Column(name="bpublish", length =255, nullable = true)
    private String bpublish;
}
