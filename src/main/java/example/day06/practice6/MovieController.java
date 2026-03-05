package example.day06.practice6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    // 조회
    @GetMapping("/movie")
    public List<MovieDto> 조회(){
        List<MovieDto> result=movieService.조회();
        return result;
    }

    // 등록
    @PostMapping("/movie")
    public boolean 등록(@RequestBody MovieDto movieDto){
        boolean result=movieService.등록(movieDto);
        return result;
    }

    // 수정
    @PutMapping("/movie")
    public boolean 수정(@RequestBody MovieDto movieDto){
        boolean result=movieService.수정(movieDto);
        return result;
    }

    // 삭제
    @DeleteMapping("/movie")
    public boolean 삭제(@RequestParam int mid){
        boolean result=movieService.삭제(mid);
        return result;
    }
}
