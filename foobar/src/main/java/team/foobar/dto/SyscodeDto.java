package team.foobar.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SyscodeDto {
    private String code;
    private String parentCode;
    private String value;

    @Builder
    public SyscodeDto(String code, String parentCode, String value) {
        this.code = code;
        this.parentCode = parentCode;
        this.value = value;
    }
}
