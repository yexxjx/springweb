package 종합.예제8.controller;

import 종합.예제8.model.dao.BoardDao;
import 종합.예제8.model.dto.BoardDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController // 해당 컨트롤러는 HTTP 기능을 갖게 됨 < 싱글톤 포함

public class BoardController {
//    private BoardController(){}
//    private static final BoardController instance=new BoardController();
//    public static BoardController getInstance() {return instance;}

    private BoardDao bd= BoardDao.getInstance();
    // [1] 게시물 등록
    @PostMapping
    public boolean write(String bcontent, String bwriter){
        boolean result=bd.write(bcontent, bwriter);
        return result;
    }

    // [2] 게시물 삭제
    @DeleteMapping
    public boolean delete(int bno){
        boolean result=bd.delete(bno);
        return result;
    }

    // [3] 게시물 수정
    @PutMapping
    public boolean update(int bno, String bcontent){
        boolean result=bd.update(bno, bcontent);
        return result;
    }

    // [4] 게시물 전체 조회, 여러 개 레코드 조회>ArrayList, 한 개 레코드 조회>Dto
    @GetMapping
    public ArrayList<BoardDto> findAll(){
        ArrayList<BoardDto> result=bd.findAll();
        return result;
    }
}
