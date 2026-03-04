package example.day05.practice5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/day05/book")
    public List<BookDto> 조회(){
        List<BookDto> result=bookService.조회();
        return result;
    }


    @PostMapping("/day05/book")
    public boolean 저장(@RequestBody BookDto bookDto){
        boolean result=bookService.저장(bookDto);
        return result;
    }

    @PutMapping("/day05/book")
    public boolean 수정(@RequestBody BookDto bookDto){
        boolean result=bookService.수정(bookDto);
        return result;
    }

    @DeleteMapping("/day05/book")
    public boolean 삭제(@RequestParam int bno){
        boolean result=bookService.삭제(bno);
        return result;
    }


}
