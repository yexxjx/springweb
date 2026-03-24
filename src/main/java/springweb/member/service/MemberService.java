package springweb.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springweb.member.dto.MemberDto;
import springweb.member.entity.MemberEntity;
import springweb.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // [1] 회원가입
    public boolean signup(MemberDto memberDto){
        MemberEntity saveEntity= memberDto.toEntity(); // 1) 저장된 dto > entity 변환
        MemberEntity savedEntity=memberRepository.save(saveEntity);
        if(savedEntity.getMno()>0){return true;}
        return false;
    }

}

/*
암호화
1. 정의: 자료를 보호하기 위해 사람이 이해하기 어려운 데이터로 변환
2. 목적: 자료보호, 신뢰성, 무결성 유지
3. 사용처: 비밀번호, 금융, HTTPS(security)
4. 용어
 1) 평문: 원래 자료
 2) 암호문: 암호화된 자료
 3) 암호화: 평문 자료를 암호문으로 변환하는 과정
 4) 복호화: 암호문 자료를 다시 평문으로 변환하는 과정
 5) 단방향: 평문을 암호문으로 반환하고 다시 평문으로 변환 불가능 <암호화>
 6) 양방향: 평문을 암호문으로 반환하고 다시 평문으로 변환 가능 <암호화/복호화>
 7) 해시함수: 자료를 고정된 길이로 변환하는 함수
    *서로 다른 자료들을 동일한 길이로 변환하는 함수
    *임의의 계산식으로 변환하는 과정이므로 다시 되돌리기가 불가능
    - 자바: .hashCode(), Object 클래스의 메소드로 객체주소값을 해시코드(일정한 길이의 값)로 반환
    - '사과' > A1B2C3
    - '사과' > A1B2C3 // 같은 재료는 같은 해시값이 나올 수 있다.
    - '바나나' > X1C2V3 // 단 서로 다른 자료도 일정한 길이로 반환한다.
    - '파인애플'> T1Y2U3
 8) 솔트: 암호화할 때 사용되는 랜덤값(동일한 계산식(알고리즘/해시) 의 서로 다른 결과값)
    - '사과' > 솔트추가 > A1B2C3
5. 종류
 1) 비밀번호: Bcrypt(비크립트), 해시함수, 단방향/복호화없음
 2) 전자서명/파일: SHA-256, 해시함수, 단방향/복호화없음, SHA-512
  - bit: 0또는1, 101=3bit
 3) 웹통신: HTTPS(TLS/SSL), HTTP(암호화안된상태) vs HTTPS(암호화된상태)

 */