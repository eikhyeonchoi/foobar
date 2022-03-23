package team.foobar.dto.file;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class FileDto {
    private Integer id;
    private String tTable;
    private Integer tId;
    private String context;

    @Builder
    public FileDto(Integer id, String tTable, Integer tId, String context) {
        this.id = id;
        this.tTable = tTable;
        this.tId = tId;
        this.context = context;
    }
}
