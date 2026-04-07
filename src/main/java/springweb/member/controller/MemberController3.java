package springweb.member.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springweb.member.dto.MemberDto;
import springweb.member.service.JWTService;
import springweb.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member3")
@CrossOrigin(value = "http://localhost:5173", allowCredentials = "true")
public class MemberController3 {
    private final MemberService memberService;
    private final JWTService jwtService; // jwt 기능 객체

    // [1] 회원가입 == 세션 방식과 동일하므로 생략

    // [2] 로그인 = 세션방식 > 토큰방식 변경 + 쿠키vs레디스(인메모리)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto loginDto, HttpServletResponse response){
        boolean result=memberService.login(loginDto);
        // 1) 만약에 로그인 성공이면 토큰 부여
        if(result) {
            // 2) 로그인 성공한 정보(아이디) 토큰에 저장
            String token = jwtService.createToken(loginDto.getMid());
            // ========= 쿠키에 토큰 담아서 응답하기 =========
            // 1) 쿠키에 토큰 담기, new Cookie("속성명", 값);
            Cookie cookie = new Cookie("token", token);
            // 2) 쿠키 옵션
            cookie.setHttpOnly(true); // .setHttpOnly(true): 쿠키 접근 방법, true이면 js가 접근 못 함
            cookie.setSecure(false); // .setSecure(true): true이면 https만 접근 가능
            cookie.setPath("/"); // .setPath(): 쿠키 접근하는 경로, "/": 전체경로
            // cookie.setMaxAge(); // .setMaxAge(초): 쿠키 유저시간

            // 3) 쿠키 응답하기, response.addCookie(쿠키객체);
            response.addCookie(cookie);
            return ResponseEntity.ok(true);
        } return ResponseEntity.ok(false);

    }

    // [3] 로그아웃 = 세션방식 > 토큰방식 변경 + 쿠키
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response){
        // 1) 매개변수에 HttpServletResponse response 받는다.
        // 2) 삭제할 쿠키의 동일한 속성명으로 null 값 저장하는 쿠키 생성
        Cookie cookie=new Cookie("token", null);
        cookie.setMaxAge(0); // 쿠키의 생명주기를 0으로 설정
        cookie.setPath("/");
        // 3) 쿠키 반환
        response.addCookie(cookie);
        // 4) 값 반환
        return ResponseEntity.ok(true);
    }
    // [4] 마이페이지 = 세션방식 > 토큰방식 변경 + 쿠키
    @GetMapping("/my/info")
    public ResponseEntity<?> myInfo(@CookieValue(value="token", required = false) String token){
        // @: HTTP 요청의 cookie 정보 매핑
        // 1) @CookieValue("token") String token 매개변수로 받는다.
        // 2) 만약에 쿠키가 없으면 비로그인
        if(token==null){return ResponseEntity.ok(false);}
        // 3) 토큰에서 값(클레임) 추출
        String mid=jwtService.getClaim(token);
        if(mid==null) return ResponseEntity.ok(false);
        // 4) 토큰에서 꺼낸 값(mid)으로 회원 정보 요청하기
        return ResponseEntity.ok(memberService.myinfo(mid));
    }
}
/*
HTTP: 문자 이동 규약/규칙
톰캣 세션: 서버, 보안 높음, 로그인
vs
쿠키: 브라우저, 보안 낮음(JWT 필요), 로그인+/장바구니/자동로그인/사용자설정(테마)
    * TOKEN: 특정한 자료 암호화(JWT) 해서 인증

 활용1) 세션은 서버에 저장하므로 *보안* 높지만 *대규모* 서버에서는 과부화 증가한다.
 활용2) 토큰은 세션/쿠키 없이 HTTP 사용하지 않으므로 앱/웹 통합 가능하다.
 활용3) 쿠키는 브라우저에 저장하므로 *보안* 낮지만 JWT와 같이 사용하며 서버에 과부하 낮을 수 있다.
 즉) 앱(JWT), 웹(JWT/cookie) 으로 일반적 사용된다.

*/