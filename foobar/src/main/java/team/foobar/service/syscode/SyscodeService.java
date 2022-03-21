package team.foobar.service.syscode;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Syscode;
import team.foobar.dto.syscode.SyscodeDto;

import java.util.List;
import java.util.Optional;

public interface SyscodeService {
    Optional<Syscode> search(String code);

    /* 0,0 이면 전체 return */
    List<Syscode> searchAll(Integer page, Integer size);

    /* 부모코드가 없을 수 있기 때문에 Optional */
    Optional<String> create(SyscodeDto dto);
    Optional<String> update(SyscodeDto dto);

    /* TODO FK 연관 삭제시 DataIntegrityViolationException exception controller에서 catch */
    void delete(String code);
    Page<Syscode> searchPage(Pageable pageable);

    List<Syscode> searchByParentCode(String parentCode);

    default Syscode dtoToEntity(SyscodeDto dt) {
        return Syscode.builder()
                .code(dt.getCode())
                .parentSys(
                        Syscode.builder()
                                .code(dt.getParentCode())
                                .build()
                )
                .value(dt.getValue())
                .build();
    }

    default SyscodeDto entityToDto(Syscode code) {
        return SyscodeDto.builder()
                .code(code.getCode())
                .parentCode(code.getParentSys().getCode())
                .value(code.getValue())
                .build();
    }
}
