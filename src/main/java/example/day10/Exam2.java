package example.day10;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Exam2 {
    public static int plus(int x, int y){return x+y;}
    public static void main(String[] args) {
        // [1] 메소드 호출
        int result= plus(3,2);
        System.out.println("result = " + result);

        // [2] 익명구현체, 구현체란? 추상메소드를 구현한 객체, 익명구현체란? 클래스 없이 구현한 인스턴스
        // 인터페이스명 변수명 = new 인터페이스명(){오버라이딩};
        Calculator calc = new Calculator() {
            @Override
            public int plus(int x, int y) { // 재정의
                return x-y;
            }
        };
        int result2=calc.plus(5,2);
        System.out.println("result2 = " + result2);

        // [3] 람다 표현식, (매개변수) -> {구현부}
        Calculator calc2 = (x,y)-> x-y; // = @Override public int plus(int x, int y) {return x-y;}
        int result3 = calc2.plus(5,2);
        System.out.println("result3 = " + result3);

        // [4] 람다 표현식을 사용하는 *함수형* 인터페이스 틀
        // < > : 제네릭이란? 인스턴스(객체) 생성할 때 인스턴스(객체)내 멤버들의 타입 정의 한다.
        // List<Integer> : List 객체 생성 시 내부에 Integer 타입으로 항목 구성하겠다는 뜻
        // Map<String, Object> : Map 객체 1개 생성 시 내부에 String 타입으로 key, Object 타입으로 value 구성하겠다는 뜻
            // 즉) 객체 내 멤버들의 타입을 매개로 정할 수 있는 타입
        // [4-1] Function<T, R> T:입력받은값타입 R:결과반환값타입, apply(T) 메소드
        Function<Integer, Integer> function= (x) -> {return x*2;}; // return 생략 시 { }도 같이 생략
        System.out.println("function.apply(3) = " + function.apply(3));

        // [4-2] Supplier<T> T:입력없이결과반환값타입
        Supplier<Double> supplier = ()-> 3.14; // return 생략 시 { } 도 같이 생략
        System.out.println("supplier.get() = " + supplier.get());

        // [4-3] Consumer<T> T:입력받은값타입이고 결과값없음, .accept(T) 메소드
        // forEach는 Consumer 기반이라 return 이 안됨.. Consumer는 소비만 하는 애라 return이 없음
        Consumer<String> consumer= (str) -> System.out.println(str); // return 존재하지 않음
        consumer.accept("유재석");

        // [4-4] Predicate<T> T:입력받아서TRUE/FALSE반환
        Predicate<Integer> predicate = (x) -> {return x%2==0;}; // { } 할거면 return 적기
        System.out.println("predicate.test(3) = " + predicate.test(3));
    }
}

interface Calculator{int plus(int x, int y);} // 추상메소드(구현이 없는 메소드.. 중괄호가 없는..)


