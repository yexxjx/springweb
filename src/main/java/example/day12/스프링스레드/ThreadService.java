package example.day12.스프링스레드;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ThreadService {
    // [1] 1초 간격으로 1부터 10까지 눚거 합계 값을 반환한다.
    public int test1(){
        int result=0;
        for(int i=1; i<=10; i++){
            System.out.println("ThreadService.test1");
            // ** 스레드 일시 정지 **
            try{Thread.sleep(1000);} // 1초간 일시정지
            catch(Exception e){System.out.println(e);}
            result += i;
        } return result;
    }

    // [2]
    @Async // 비동기: 먼저 응답/반환하고 내부적으로 처리
    // AppStart 클래스 위에 @EnableAsync 활성화해야 됨
    public void test2(){
        int result=0;
        for(int i=1; i<=10; i++){
            System.out.println("ThreadService.test2");
            try{Thread.sleep(1000);}
            catch(Exception e){System.out.println(e);}
            result += i;
        }
        // return값 없이 결과값 출력
        System.out.println("result = " + result);
    }
}