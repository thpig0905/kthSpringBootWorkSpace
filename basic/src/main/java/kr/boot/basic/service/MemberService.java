package kr.boot.basic.service;

import jakarta.transaction.Transactional;
import kr.boot.basic.domain.Member;
import kr.boot.basic.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long join(Member member) {
        if (validateDuplicateMember(member)) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        } else {
            memberRepository.save(member);
            return member.getId();
        }
    }

    private boolean validateDuplicateMember(Member member) {
        return memberRepository.findByName(member.getName()).isPresent();
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}