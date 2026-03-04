package example.day05.practice5;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<BookDto> 조회(){
        List<BookEntity> bookEntityList=bookRepository.findAll();
        List<BookDto> bookDtoList=new ArrayList<>();
        bookEntityList.forEach(bookEntity -> {
            BookDto bookDto=new BookDto();
            bookDto.setBno(bookEntity.getBno());
            bookDto.setBid(bookEntity.getBid());
            bookDto.setBname(bookEntity.getBname());
            bookDto.setBwriter(bookEntity.getBwriter());
            bookDto.setBpublish(bookEntity.getBpublish());
            bookDtoList.add(bookDto);
        });
        return bookDtoList;
    }

    public boolean 저장(BookDto bookDto){
        BookEntity bookEntity=new BookEntity();
        bookEntity.setBid(bookDto.getBid());
        bookEntity.setBname(bookDto.getBname());
        bookEntity.setBwriter(bookDto.getBwriter());
        bookEntity.setBpublish(bookDto.getBpublish());
        BookEntity savedEntity=bookRepository.save(bookEntity);
        if(savedEntity.getBno()>=1) return true;
        return false;
    }

    public boolean 수정(BookDto bookDto) {
        Optional<BookEntity> optional = bookRepository.findById(bookDto.getBno());
        if(optional.isPresent()){
            BookEntity bookEntity=optional.get();
            bookEntity.setBid(bookDto.getBid());
            bookEntity.setBname(bookDto.getBname());
            bookEntity.setBwriter(bookDto.getBwriter());
            bookEntity.setBpublish(bookDto.getBpublish());
            return true;
        } return false;
    }

    public boolean 삭제(int bno){
        bookRepository.deleteById(bno);
        return true;
    }
}
