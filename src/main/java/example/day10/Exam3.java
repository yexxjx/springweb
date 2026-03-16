package example.day10;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Exam3 {
    public static void main(String[] args) {
        // 람다 표현식, (매개변수) -> {구현부}
        // 스트링 API: 데이터(매개변수) > 중간연산 > 최종출력

        List<Integer> number=List.of(1,2,3,4,5,6,7,8,9,10); // 임의의 데이터를 담고 있는 리스트

        // [1] 리스트변수명.stream().forEach(); , 중간 연산 없이 바로 최종 출력
        // 매개 변수에 반복 변수를 하나씩 대입하여 return 없는 반복문 구현
        number.stream().forEach((x) -> {System.out.println(x*2);} );

        // [2] 리스트변수명.stream().map(중간연산).collect(최종연산)
        // 매개변수에 반복변수를 하나씩 대입하여 return 있는 반복문, 반복 return 값을 collect(Collectors.toXXX) 변환 받음
        List<Integer> result = number
                .stream().map((x) -> {return x*2;}) // 중간연산
                .collect(Collectors.toList());             // 중간연산 결과를 새로운 리스트에 반환 해준다.
        System.out.println("result = " + result);

        // [3] 리스트변수명.stream().map(중간연산).forEach(최종연산)
        number.stream()
                .map((x) -> x*2)
                .forEach((result1) -> {System.out.println("result1 = " + result1);}); // 중간연산 결과물

        // [4] 리스트변수명.stream().filter(중간연산).forEach(최종연산)
        number.stream() // 리스트 내 데이터들의 흐름 시작
                .filter(x -> x%2==0) // 중간연산, 짝수찾기 반환
                .forEach(y -> System.out.println("y = " + y)); // 최종출력

        // [5] 리스트변수명.stream().sorted(중간연산).forEach(최종연산)
        number.stream() // 리스트 내 데이터들의 흐름 시작
                .sorted(Comparator.reverseOrder()) // 내림차순
                .forEach(y -> System.out.println("y = " + y));

        // [6]
        number.stream()
                .distinct() // 중간연산, 중복제거
                .collect(Collectors.toList());

        // [7]
        number.stream()
                .limit(5) // 중간연산, 앞에서부터N개까지(갯수제한)
                .forEach(y -> System.out.println("y = " + y));

        /*
            스트림이란? 데이터 다니는 연속적인 흐름
             - 데이터들 --> 중간연산 --> 최종연산
             - 중간연산은 여러개 가능
             - 최종출력은 무조건 반드시 1개
            주요 연산
             - 중간 연산: .map() .filter() .sorted() .distinct() .limit
             - 최종 연산: foreach() collect()
         */

        /*
        [1] for문
        List<BoardDto> list = new ArrayList<>(); // 여러개 dto 담는 리스트
        for( int i = 0 ; i<=entityList.size()-1; i++ ){
            BoardDto boardDto = entityList.get(i).toDto();
            list.add( boardDto );
        }

        [2] forEach문
        List<BoardDto> list = new ArrayList<>(); // 여러개 dto 담는 리스트
        entityList.forEach( entity -> {  // 리스트변수명.forEach( 반복변수 -> { 실행문; } );
            BoardDto boardDto = entity.toDto(); // 3] 엔티티 하나씩 dto로 변환
            list.add( boardDto ); // 4] 새로운 리스트에 담기
        } );

        [3] 람다표현식
        List<BoardDto> list =
                entityList.stream().map( entity -> entity.toDto() ).collect( Collectors.toList() );

        [4[ 래퍼런스API
        List<BoardDto> list=entityList.stream().map(entity :: toDto)
 */
    }
}
