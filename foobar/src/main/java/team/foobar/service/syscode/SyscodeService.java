package team.foobar.service.syscode;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Syscode;
import team.foobar.dto.syscode.SyscodeDto;

import java.util.List;
import java.util.Optional;

public interface SyscodeService {
    Optional<Syscode> search(String code);
    Page<Syscode> searchPage(Pageable pageable);
    /* 부모코드가 없을 수 있기 때문에 Optional */
    Optional<String> create(SyscodeDto dto);
    Optional<String> update(SyscodeDto dto);

    /* TODO FK 연관 삭제시 DataIntegrityViolationException exception controller에서 catch */
    void delete(String code);

    List<Syscode> searchByParentCode(String parentCode);

    default Syscode dtoToEntity(SyscodeDto dto) {
        Syscode.SyscodeBuilder builder = Syscode.builder()
                .code(dto.getCode())
                .value(dto.getValue());

        if(dto.getParentCode() != null) {
            builder.parentSys(Syscode.builder().code(dto.getParentCode()).build());
        }

        return builder.build();
    }

    default SyscodeDto entityToDto(Syscode code) {
        return SyscodeDto.builder()
                .code(code.getCode())
                .parentCode(code.getParentSys().getCode())
                .value(code.getValue())
                .build();
    }
}
