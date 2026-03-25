package springweb.board.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springweb.board.dto.BoardDto;
import springweb.board.service.BoardService;
import springweb.member.service.JWTService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;
    private final JWTService jwtService;

    // 회원제 글 등록+세션정보
    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody BoardDto boardDto, HttpSession session) {
        // 1) 세션내 로그인 정보 확인
        Object object = session.getAttribute("loginMid");
        if (object == null) {
            return ResponseEntity.ok(false);
        } // 만약에 비로그인이면 글쓰기 실패
        // 2) 로그인 중이면
        String loginMid = (String) object;
        // 3) 서비스에게 입력받은 값과 세션에 저장된 값 전달
        boolean result = boardService.write(boardDto, loginMid);
        return ResponseEntity.ok(result);
    }

    // 회원제 글 등록+토큰정보
    @PostMapping("/write2")
    public ResponseEntity<?> write2(@RequestBody BoardDto boardDto, @RequestHeader("Authorization") String token) {
        // 1) 매개변수로 jwt토큰 받는다.
        // 2) 만약에 토큰이 없거나 Bearer 시작하지 않으면, 문자열 .startsWith("시작문자"): 문자열 내 시작 문자가 존재하면 true
        if (token == null || !token.startsWith("Bearer")) {
            return ResponseEntity.ok(false); // 비로그인으로 글쓰기 실패
        }
        // * 토큰만 추출
        token=token.replace("Bearer ","");
        // 3) 토큰에서 클레임(값) 꺼내기
        String loginMid = jwtService.getClaim(token);
        if (loginMid == null) {return ResponseEntity.ok(false);}
        boolean result = boardService.write(boardDto, loginMid);
        return ResponseEntity.ok(result);
    }
}


/*
application/json 이란? 주로 POST/PUT method 에서 Body(본문)에 포함하는 내용물들의 타입
 */