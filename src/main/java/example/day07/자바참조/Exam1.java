package example.day07.자바참조;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class Exam1 {
    public static void main(String[] args) {
        // 자바는 객체 지향 언어이다.
        // System: 클래스, System out: 객체, prinln(): 함수/메소드
        // 즉) 특정한 객체가 특정한 함수 실행한다.
        System.out.println("출력함수");

        // [1] 카테고리 객체 생성 == 클래스(설계도)로 메모리(인스턴스) 생성 뜻
        // 동일한 클래스(설계)로 서로 다른 객체 생성
        Category category=new Category(); // 101호
        category.setCno(1); category.setCname("공지사항");

        Category category2=new Category(); // 102호
        category2.setCno(2); category2.setCname("자유게시판");

        // [2] 게시물 객체 생성, 참조란? 다른 값 접근한다.
        // 즉) ORM(JPA) 기술은 이러한 객체참조로 데이터베이스의 PK-FK구현
        // 단방향: FK 통해 PK 참조하는 것처럼, 특정한 객체 통해 참조 객체를 참조한다.
        // 즉) 게시물 객체 통해 카테고리 객체를 참조한다. 카테고리 객체를 통해 게시물 객체는 참조 못한다.
            // 2-1: 첫번째 게시물은 공지사항이다.
        // 첫번째 카테고리 객체를 게시물 객체에 대입
            Board board=new Board(); // 201호
            board.setBcontent("첫번째 게시물 입니다.");
            board.setCategory(category); // 201호 안에 102호 참조
            // 2-2: 두번째 게시물은 자유게시판이다.
            Board board2=new Board(); // 202호
            board2.setBcontent("두번째 게시물입니다.");
            board2.setCategory(category2); // 202호 안에 102호 참조

        // [3] 카테고리가 게시물 참조
        // 양방향: 두 객체간의 서로 참조하는 관계, DB 존재하지 않음
        // 즉) ORM(JPA)는 단방향과 양방향을 모두 지원함, 특징: 조회가 빠름, JOIN 필요없음
        // 1. 순환 참조 문제점 고려 2. 복잡한 JOIN 3. LAZY 지연 로딩 고려
        category.getBoardList().add(board); // 101호에 201호 참조 대입
        category2.getBoardList().add(board2); // 102호에 202호 참조 대입

        // FK에서 PK 단방향 참조/조회
        System.out.println(board2.getCategory());
        // PK에서 FK 양방향 참조/조회
        // StackOverflowError 양방향 참조하다가 메모리 넘쳤다
        // 해결방안: 두 객체 간의 한 쪽 필드에 @ToString.Exclude 적용
        System.out.println(category.getBoardList());
    }
}

@Data // 롬북
class Category{
    private int cno; // 카테고리 번호
    private String cname; // 카테고리 이름
    @ToString.Exclude // 순환 참조 방지
    private List<Board> boardList=new ArrayList<>(); // 게시물 목록
}

@Data
class Board{
    private int bno;
    private String bcontent;
    private Category category; // 다른 객체를 참조하겠다는 의미
}
