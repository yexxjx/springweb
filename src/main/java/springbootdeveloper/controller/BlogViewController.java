package springbootdeveloper.controller;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springbootdeveloper.dto.ArticleListViewResponse;
import springbootdeveloper.service.BlogService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogViewController {
    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles=blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);
        return  "articleList";
    }
}
