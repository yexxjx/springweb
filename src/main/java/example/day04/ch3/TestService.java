package example.day04.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 서비스 또는 비즈니스 개체
public class TestService {
    @Autowired
    MemberRepository memberRepository;
    public List<Member> getAllMembers(){
        return memberRepository.findAll(); // select
    }

    public boolean saveMember(){
        Member member=new Member(0l, "유재석");
        memberRepository.save(member); // insert
        return true;
    }
}
