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

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Primary
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repository;

    @Override
    public Optional<Member> search(Integer memberId) {
        return repository.findById(memberId);
    }

    @Override
    public List<Member> searchAll() {
        return repository.findAllWithFetch();
    }

    @Override
    @Transactional
    public Optional<Integer> create(MemberDto dto) {
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
    public Optional<Member> getByNickname(String nickname) {
        return repository.findByNickname(nickname);
    }

    @Override
    public Optional<Member> getByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional
    public void delete(Integer memberId) {
        repository.deleteById(memberId);
    }

    @Override
    public Page<Member> searchPage(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
