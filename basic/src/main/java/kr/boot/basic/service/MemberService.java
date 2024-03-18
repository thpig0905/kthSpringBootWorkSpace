package kr.boot.basic.service;

import kr.boot.basic.domain.Member;
import kr.boot.basic.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

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