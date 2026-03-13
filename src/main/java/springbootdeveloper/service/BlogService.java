package springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootdeveloper.domain.Article;
import springbootdeveloper.dto.AddArticleRequest;
import springbootdeveloper.dto.UpdateArticleRequest;
import springbootdeveloper.repository.BlogRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    /* 책 버전
    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found:"+id));
    } */

    // 교수님 버전
    public Article findById(long id){
        Optional<Article> optionalArticle=blogRepository.findById(id);
        if(optionalArticle.isPresent()){
            return optionalArticle.get();
        } return null;
    }

    public void delete(long id){
        blogRepository.deleteById(id);
    }

    // 책 버전
    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article=blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found:"+id));
        article.update(request.getTitle(),request.getContent());
        return article;
    }

    // 교수님 버전
    /*
    public Article update(long id, UpdateArticleRequest request){
        Optional<Article>optionalArticle=blogRepository.findById(id);
        if(optionalArticle.isPresent()){
            Article article=optionalArticle.get();
            article.update(request.getTitle(),request.getContent());
            return article;
        } return null;
    }
    */
}
