package 종합.예제10.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import 종합.예제10.board.dto.CommentDto;
import 종합.예제10.board.service.CommentService;

import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public boolean 댓글등록(@RequestBody CommentDto commentDto){
        boolean result=commentService.댓글등록(commentDto);
        return result;
    }

    @GetMapping("/comment")
    public List<CommentDto> 댓글조회(@RequestParam int bno){
        List<CommentDto> result= commentService.댓글조회(bno);
        return result;
    }

    @PutMapping("/comment")
    public boolean 댓글수정(@RequestBody CommentDto commentDto){
        boolean result=commentService.댓글수정(commentDto);
        return result;
    }

    @DeleteMapping("/comment")
    public boolean 댓글삭제(@RequestParam int cno){
        boolean result=commentService.댓글삭제(cno);
        return result;
    }

}
