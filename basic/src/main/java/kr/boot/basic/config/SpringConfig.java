package kr.boot.basic.config;

import jakarta.persistence.EntityManager;
import kr.boot.basic.repository.JpaMemberRepository;
import kr.boot.basic.repository.MemberRepository;
import kr.boot.basic.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@RequiredArgsConstructor
//@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    private final EntityManager em;

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JDBCMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }

    public MemberService memberService(MemberRepository repository) {
        return new MemberService(repository);
    }
}
