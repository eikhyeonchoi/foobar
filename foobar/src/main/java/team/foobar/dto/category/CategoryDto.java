package team.foobar.dto.category;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryDto {
    private Integer id;
    private String syscode;
    private String syscodeName;
    private String name;
    private Integer ord;
    private Boolean useFl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime createDt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime updateDt;

    @Builder
    public CategoryDto(Integer id, String syscode, String syscodeName, String name, Integer ord, Boolean useFl, LocalDateTime createDt, LocalDateTime updateDt) {
        this.id = id;
        this.syscode = syscode;
        this.syscodeName = syscodeName;
        this.name = name;
        this.ord = ord;
        this.useFl = useFl;
        this.createDt = createDt;
        this.updateDt = updateDt;
    }
}
