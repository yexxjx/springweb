package example.종합.예제9.controller;

import example.종합.예제9.model.dao.BoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController // 빈 등록+HTTP 기능+HTTP 응답 객체
// component+responsebody
public class BoardController {

    @Autowired private BoardDao boardDao;
}
