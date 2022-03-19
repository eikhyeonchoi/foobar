package team.foobar.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Member;
import team.foobar.repository.jpa.member.MemberRepository;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Primary
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repository;

    @Override
    public Member search(Integer memberId) {
        return repository.findById(memberId).orElse(null);
    }

    @Override
    public List<Member> searchAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Member create(Member member) {
        return repository.save(member);
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
