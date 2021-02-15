package springexproject.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import springexproject.practice.domain.Member;
import springexproject.practice.repository.MemberRepository;
import springexproject.practice.repository.MemoryMemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Long join(Member member) {
        memberRepository.save(member);
        //validateDuplicateMember(member);
        return member.getId();
    }

    public void validateDuplicateMember(Member member) {
        memberRepository.findById(member.getId())
                .ifPresent(member1 -> {
                    throw new IllegalStateException("이미 존재하는 아이디 입니다.");
                });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
