package example.day05.mvc;

import org.aspectj.weaver.patterns.IfPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static aQute.bnd.annotation.headers.Resolution.optional;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    // C insert
    public boolean 저장(ExamDto examDto){
        // insert 대신에 JPA 함수 사용
        // .save(Entity): 해당 Entity를 insert 한다
        // 1) 저장할 DTO > Entity 반환
        ExamEntity examEntity=new ExamEntity();
        examEntity.setEname(examDto.getEname());
        // 2) .save 이용한 insert하고 처리한 entity 반환, pk여부 확인
        ExamEntity savedEntity=examRepository.save(examEntity);
        // 3) 처리한 entity의 pk여부
        if(savedEntity.getEno()>=1) return true; // pk가 1보다 크면 성공
        return false;
    }

    // R select
    public List<ExamDto>전체조회(){
        // select 대신에 JPA 함수 사용
        // findAll: 전체 조회
        List<ExamEntity> examEntityList=examRepository.findAll();
        // Entity > Dto 변환 <Entity 노출 안하기 위해서
        List<ExamDto> examDtoList=new ArrayList<>(); // 1) 여러개 Dto 저장할 리스트 선언
        examEntityList.forEach(examEntity -> { // 2) 반복문 for() VS forEach(반복변수 -> {실행문;})
            ExamDto examDto=new ExamDto(); // 3) Dto 선언
            examDto.setEno(examEntity.getEno()); // 4) 반복중인 Entity 객체 내 멤버변수를 Dto에 저장
            examDto.setEname(examEntity.getEname()); // 4) 반복중인 Entity 객체 내 멤버변수를 Dto에 저장
            examDtoList.add(examDto); // 5) Dto를 리스트에 저장
        });
        return examDtoList; // ** Entity가 아닌 Dto 반환. 왜? 서비스개발자만 Entity 다룬다. 역할과 조작 권한의 차이

    }
    // U update
    public boolean 수정(ExamDto examDto){
        // update 대신에 JPA 영속성(계속되는 성질/능력) 사용
        // 데이터베이스 외 자바 객체 연결되는 상태를 계속적으로 유지
        // 즉) 자바 객체가 수정되면 데이터베이스도 자동으로 수정
        // 1) 수정할 Entity 찾기, PK
        Optional<ExamEntity> optional= examRepository.findById(examDto.getEno()); // findById(수정할PK번호);
        // 2) 만약에 엔티티가 존재하면 .isPresent(): 조회 결과가 있으면 true, 없으면 false
        if(optional.isPresent()){
            ExamEntity examEntity=optional.get(); // 존재한 Entity 꺼내기
            // =================== 영속성 이용한 수정 ===================
            examEntity.setEname(examDto.getEname()); // 입력받은(수정할) 값을 Entity에 Setter 이용하여 수정한다.
            return true;
        } return false;
    }

    // D delete
    public boolean 삭제(int eno){
        // delete 대신에 JPA 함수 사용
        // .deleteById(삭제할pk번호) // 해당하는 pk가 존재하면 삭제
        examRepository.deleteById(eno);
        return true;
    }
}
