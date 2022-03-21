package team.foobar.service.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Member;
import team.foobar.domain.Syscode;
import team.foobar.dto.member.MemberDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Optional<Member> search(Integer id);
    /* 0,0 이면 전체 return */
    List<Member> searchAll(Integer page, Integer size);
    Optional<Integer> create(MemberDto dto);
    Optional<Integer> update(MemberDto dto);
    Optional<Member> searchByNickname(String nickname);
    Optional<Member> searchByEmail(String email);
    void delete(Integer id);
    Page<Member> searchPage(Pageable pageable);

    default Member dtoToEntity(MemberDto dto) {
        return Member.builder()
                .id(dto.getId())
                .roleSys(Syscode.builder().code(dto.getSyscode()).build())
                .email(dto.getEmail())
                .pwd(dto.getPwd())
                .nickname(dto.getNickname())
                .refreshToken(dto.getToken())
                .build();
    }

    default MemberDto entityToDto(Member m) {
        return MemberDto.builder()
                .id(m.getId())
                .syscode(m.getRoleSys().getCode())
                .syscodeValue(m.getRoleSys().getValue())
                .email(m.getEmail())
                .nickname(m.getNickname())
                .build();
    }
}
