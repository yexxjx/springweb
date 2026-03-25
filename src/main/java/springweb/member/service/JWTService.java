package springweb.member.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JWTService {

    // [*] 비밀키 정의
    private String secret="123456789123456789123456789123456789"; // 개발자가 임의로 32글자 이상의 문자열 구성
    private Key secretKey= Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); // SHA-256 알고리즘으로 비밀키 생성

    // [1] 토큰 발급: 특정한 자료를 이해하기 어려운 자료로 변경
    public String createToken(String mid){ // 토큰으로 가져올 거 넣기 만약 멤버 전체면 DTO 가져오면 됨
        String token= Jwts.builder() // 토큰객체 생성 빌더 시작
                .claim("mid", mid) // key와 value 쌍으로 토큰에 저장할 값
                // .claim(key, value) 키 더 있으면 더 넣기 가능
                .setIssuedAt(new Date()) // 토큰 발급날짜/시간
                .setExpiration(new Date(System.currentTimeMillis()*1000*60*60*24)) // 토큰 유지/유효 시간, 밀리초*1000=1초, *60=1분, *60=1시간, *24=24시간
                .signWith(secretKey, SignatureAlgorithm.HS256) // 토큰에 비밀키 넣고
                .compact(); // 토큰객체 생성 빌더 종료
        return token;
    }

    // [2] 토큰의 클레임(내용물) 추출
    public String getClaim(String token){ // 토큰값 받기
        try {
            Claims claims=Jwts.parser()
                    .setSigningKey(secretKey) // 비밀키 입력
                    .build() // 비밀키 일치하지 않으면 예외발생
                    .parseClaimsJws(token) // 서명확인할 토큰 대입
                    .getBody(); // 서명확인 토큰 내 클레임(내용물) 반환/없으면 예외 발생
            Object object=claims.get("mid"); // 클레임(내용물)의 값은 모두 Object이다. 세션
            return (String) object;
        }catch (Exception e){System.out.println(e);}
        return null; // 토큰이 없거나 유효하지 않을 때
    }

}