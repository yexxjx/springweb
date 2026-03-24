package example.day15;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
public class JWTController {
    private final JWTService jwtService;

    // [1] JWT 토근 생성 == 데이터를 암호화 하기 == 로그인정보 vs 세션(HTTP)
    @GetMapping("/create") // http://localhost:8080/api/jwt/create?data=바나나
    public ResponseEntity<?> create(@RequestParam String data){
        return ResponseEntity.ok(jwtService.create(data));
    }

    // [2] JWT 토큰 값 추출 == 암호화된 JWT 토큰을 다시 평문으로
    @GetMapping("/read")
    public ResponseEntity<?> read(@RequestParam String token){
        return ResponseEntity.ok(jwtService.read(token));
    }
}
