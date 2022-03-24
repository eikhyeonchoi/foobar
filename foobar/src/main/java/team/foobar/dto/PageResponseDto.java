package team.foobar.dto;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class PageResponseDto<T> {
    private Integer totalSize;
    private T data;
}
