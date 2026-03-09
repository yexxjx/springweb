package example.day07.practice7.dto;

import example.day07.practice7.entity.EnrollEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EnrollDto {
    private Integer enrollId;
    private String status;

    private String createDate;
    private String updateDate;

    public EnrollEntity toEntity(){
        return EnrollEntity.builder()
                .enrollId(enrollId)
                .status(status)
                .build();
    }
}
