package 종합.예제10.board.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import 종합.예제10.board.dto.CommentDto;
import 종합.예제10.board.entity.CommentEntity;
import 종합.예제10.board.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public boolean 댓글등록(CommentDto commentDto){
        CommentEntity savedEntity=commentDto.toEntity();
        savedEntity=commentRepository.save(savedEntity);
        if(savedEntity.getCno()>=1) return true;
        return false;
    }

    public List<CommentDto> 댓글조회(int bno){
        List<CommentEntity> entityList=commentRepository.findAll();
        List<CommentDto> list=new ArrayList<>();
        entityList.forEach(entity -> {
            if(entity.getBno()==bno) {
                CommentDto commentDto = entity.toDto();
                list.add(commentDto);
            }});
        return list;
    }

    @Transactional
    public boolean 댓글수정(CommentDto commentDto){
        Optional<CommentEntity> optional= commentRepository.findById(commentDto.getCno());
        if(optional.isPresent()){
            CommentEntity updateEntity=optional.get();
            updateEntity.setCno(commentDto.getCno());
            updateEntity.setCwriter(commentDto.getCwriter());
            updateEntity.setCcontent(commentDto.getCcontent());
            return true;
        } return false;
    }

    public boolean 댓글삭제(int cno){
        boolean exists=commentRepository.existsById(cno);
        if(exists==true){
            commentRepository.deleteById(cno);
            return true;
        } return false;
    }
}
