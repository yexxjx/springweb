package springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springbootdeveloper.domain.Article;
import springbootdeveloper.dto.AddArticleRequest;
import springbootdeveloper.dto.ArticleResponse;
import springbootdeveloper.dto.UpdateArticleRequest;
import springbootdeveloper.service.BlogService;

import java.util.List;

@RequiredArgsConstructor
@Controller // ioc, 빈등록, @Controller+@ResponseBody 포함
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle=blogService.save(request);

        return ResponseEntity.status(201)
                .body(savedArticle);

        // return ResponseEntity.status(응답코드).body(반환값)
        // vs
        // return 반환값
    }

    // 책 버전
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles=blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }
    /* 교수님 버전
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<Article> articleList=blogService.findAll();
        List<ArticleResponse> articleResponses=new ArrayList<>();
        articleList.forEach((entity)->{
            ArticleResponse articleResponse=new ArticleResponse(entity);
            articleResponse.add(articleResponse);
        });
        return ResponseEntity.status(200).body(articleResponses);
    } */

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        Article article=blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request){
        Article updatedArticle=blogService.update(id,request);

        return ResponseEntity.ok()
                .body(updatedArticle);
    }
}
