package team.foobar.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Member;
import team.foobar.domain.Syscode;
import team.foobar.dto.member.MemberDto;
import team.foobar.repository.jpa.member.MemberRepository;
import team.foobar.repository.jpa.syscode.SyscodeRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Primary
public class MemberServiceImpl implements MemberService {
    private final MemberRepository repository;
    private final SyscodeRepository syscodeRepository;


    @Override
    public Optional<Member> search(Integer id) {
        return repository.findByIdWithFetch(id);
    }

    @Override
    public Page<Member> searchPage(Pageable pageable) {
        return repository.findAllWithFetch(pageable);
    }

    @Override
    @Transactional
    public Optional<Integer> create(MemberDto dto) {
        Optional<Syscode> syscode = syscodeRepository.findById(dto.getSyscode());
        if(syscode.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(repository.save(dtoToEntity(dto)).getId());
    }

    @Override
    @Transactional
    public Optional<Integer> update(MemberDto dto) {
        Optional<Member> searchMember = this.search(dto.getId());
        if(searchMember.isEmpty()) {
            return Optional.empty();
        }

        Member member = searchMember.get();
        member.change(Syscode.builder().code(dto.getSyscode()).build(), dto.getEmail(), dto.getPwd(), dto.getNickname(), dto.getToken());
        return Optional.of(member.getId());
    }

    @Override
    public Optional<Member> searchByNickname(String nickname) {
        return repository.findByNicknameWithFetch(nickname);
    }

    @Override
    public Optional<Member> searchByEmail(String email) {
        return repository.findByEmailWithFetch(email);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
