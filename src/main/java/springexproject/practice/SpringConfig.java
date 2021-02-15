package springexproject.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springexproject.practice.repository.JdbcTemplateMemberRepository;
import springexproject.practice.repository.JpaMemberRepository;
import springexproject.practice.repository.MemberRepository;
import springexproject.practice.repository.MemoryMemberRepository;
import springexproject.practice.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

   private EntityManager em;

   @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memoryMemberRepository());
    }

    @Bean
    public MemberRepository memoryMemberRepository() {
          //return new JdbcTemplateMemberRepository(dataSource);
          //return new MemoryMemberRepository();
        return new JpaMemberRepository(em);
    }
}
