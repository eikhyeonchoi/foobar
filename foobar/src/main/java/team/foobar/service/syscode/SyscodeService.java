package team.foobar.service.syscode;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Syscode;
import team.foobar.dto.syscode.SyscodeDto;

import java.util.List;
import java.util.Optional;

public interface SyscodeService {
    public Optional<Syscode> search(String code);
    public Optional<Syscode> searchNoFetch(String code);


    public List<Syscode> searchAll();

    /* 부모코드가 없을 수 있기 때문에 Optional */
    public Optional<String> create(SyscodeDto dto);
    public Optional<String> update(SyscodeDto dto);

    public void delete(String code);
    public Page<Syscode> searchPage(Pageable pageable);

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
