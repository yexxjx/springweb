package example.종합.예제9.controller;

import example.종합.예제9.model.dao.BoardDao;
import example.종합.예제9.model.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 빈 등록+HTTP 기능+HTTP 응답 객체
// component+responsebody
public class BoardController {
    @Autowired
    private BoardDao boardDao;
    @GetMapping("/board") // localhost:8080/board      // 1. 해당 메소드의 HTTP 메소드와 주소 정의
    public List<BoardDto> findAll(){                   // 2. 매개변수 정의 *현재 없음
        List<BoardDto> result=boardDao.findAll();      // 3. DAO 호출하여 결과 받기
        return result;                                 // 4. DAO 결과로 응답하기
    }

    @PostMapping("/board")
    public boolean write(@RequestBody BoardDto boardDto) {
        boolean result = boardDao.write(boardDto);
        return result;
    }

    @PutMapping("/board")
    public boolean update(@RequestBody BoardDto boardDto){
        boolean result = boardDao.update(boardDto);
        return result;
    }

    @DeleteMapping("/board")
    public boolean delete(@RequestParam int bno){
        boolean result = boardDao.delete(bno);
        return result;
    }


}
