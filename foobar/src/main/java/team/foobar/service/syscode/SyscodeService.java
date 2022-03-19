package team.foobar.service.syscode;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Syscode;
import team.foobar.dto.SyscodeDto;

import java.util.List;

public interface SyscodeService {
    public Syscode search(String code);
    public List<Syscode> searchAll();
    public Syscode create(SyscodeDto dto);
    /* 부모코드가 없을 수 있기 때문에 Integer return */
    public Integer update(SyscodeDto dto);
    public void delete(String code);
    public Page<Syscode> searchPage(Pageable pageable);

    default Syscode DtoToEntity(SyscodeDto dt) {
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

    default SyscodeDto EntityToDto(Syscode code) {
        return SyscodeDto.builder()
                .code(code.getCode())
                .parentCode(code.getParentSys().getCode())
                .value(code.getValue())
                .build();
    }
}
