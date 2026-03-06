package 종합.예제10.board.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import 종합.예제10.board.dto.BoardDto;
import 종합.예제10.board.entity.BoardEntity;
import 종합.예제10.board.repository.BoardRepository;

import javax.swing.text.html.Option;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    public boolean 등록(BoardDto boardDto){
        // 1) 저장할 dto > entity 변환
        BoardEntity savedEntity=boardDto.toEntity();
        // 2. JPA 이용한 entity 저장
        savedEntity= boardRepository.save(savedEntity);
        // 3. Pk 생성여부 판단
        if(savedEntity.getBno()>=1) return true;
        return false;
    }

    public List<BoardDto> 전체조회(){
        // 1) JPA 이용한 모든 엔티티 조회
        List<BoardEntity> entityList=boardRepository.findAll();
        // 2) 조회된 모든 entity > dto 변환
        List<BoardDto> list=new ArrayList<>();
        // 리스트변수명.forEach(반복변수->{실행문;});
        entityList.forEach(entity -> {
            // 3) entity 하나씩 dto로 변환
            BoardDto boardDto=entity.toDto();
            list.add(boardDto);
        });
        return list;
    }

    public BoardDto 개별조회(int bno){
        // 1) 조회할 번호 pk 의 entity 찾기
        Optional<BoardEntity> optional=boardRepository.findById(bno);
        // 2) 만약에 entity가 존재하면
        if(optional.isPresent()){
            BoardEntity entity=optional.get();
            BoardDto boardDto=entity.toDto();// 3) entity > dto 변환
            return boardDto; // 4) 반환, 조회 성공
        }
        return null; // 4) 반환, 조회 실패
    }

    @Transactional
    public boolean 개별수정(BoardDto boardDto){
        // 1) 수정할 게시물 번호로 entity 찾기
        Optional<BoardEntity> optional=boardRepository.findById(boardDto.getBno());
        // 2) 만약에 entity가 존재하면
        if(optional.isPresent()){
            // 3) entity내 멤버 변수 수정 <영속성>
            BoardEntity updateEntity=optional.get();
            updateEntity.setBtitle(boardDto.getBtitle());
            updateEntity.setBcontent(boardDto.getBcontent());
            updateEntity.setBwriter(boardDto.getBwriter());
            return true; // 4) 반환
        }
        return false; // 4) 반환
    }

    public boolean 개별삭제(int bno) {
        // 1) 삭제할 게시물 번호가 존재하는지
        boolean exists = boardRepository.existsById(bno);
        // 2) 만약에 존재하면 삭제
        if (exists == true) {
            boardRepository.deleteById(bno);
            return true;
        }
        return false;
    }
}
