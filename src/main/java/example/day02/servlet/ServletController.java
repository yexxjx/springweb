package example.day02.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.io.IOException;

// 서블릿이란? 자바 회사에서 HTTP웹 동적 처리 하기 위한 리이브러리(미리 만든)/클래스
// 사용법
// 1. 현재 클래스의 HttpServlet 클래스로부터 상속 받기
// 2. 현재 클래스 위에 @WebServlet("/URL") 주소 설정함, 중복 없이 (영문으로) 아무거나 가능
// 3. (스프링 환경에만 해당) AppStart 클래스 위에 @ServletComponentScan 주입하여 스프링이 서블릿 찾을 수 있도록 함
@WebServlet("/day02/servlet") // localhost:8080/day02/servlet 요청 시 통신 가능한 클래스 정의
public class ServletController extends HttpServlet {
    // 순수 자바의 메소드/함수
    boolean method(int param){return true;}

    // 1. 서블릿 클래스로부터 HTTP 요청 시 init 함수 최초 1번 실행
//    @Override
//    public void init() throws ServletException {
//        System.out.println("init 함수 실행");
//        super.init();
//    }
//
//    // 2. 서블릿 클래스로부터 HTTP 요청 마다 service 함수 실행
//    @Override
//    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//        System.out.println("service 함수 실행");
//        super.service(req, res);
//    }

    // 3. HTTP 메소드란? GET, POST, PUT, DELETE
    // 서블릿 객체는 요청이 끝나도 사라지지 않는다. 다음 요청에 재사용
    // HttpServletResponse(요청 정보), HttpServletResponse(응답 정보) 객체는 요청이 끝나면 사라짐
    // 3-1. GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doGet"); // soutm: 현재 메소드 출력
        String data=req.getParameter("data");
        // * HTTP 요청 시 포함된 매개변수 확인: http://localhost:8080/day02/servlet?data=아무값
        System.out.println("data = " + data);
        // * HTTP 요청 시 클라이언트 응답
        resp.getWriter().println("Client Response : Hi~");
    }
    // 3-2. POST, 브라우저의 주소 입력창에 요청은 무조건 GET 방식이므로 POST PUT DELETE는 포스트맨VS탈렌드API 사용
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doPost");
        String data=req.getParameter("data");
        System.out.println("data = " + data);
        resp.getWriter().println("Client Response : Hi~");
    }
    // 3-3. PUT
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doPut");
        String data=req.getParameter("data");
        System.out.println("data = " + data);
        resp.getWriter().println("Client Response : Hi~");
    }
    // 3-4. DELETE
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doDelete");
        String data=req.getParameter("data");
        System.out.println("data = " + data);
        resp.getWriter().println("Client Response : Hi~");
    }
    // HTTP란? 클라이언트와 서버 간의 통신을 하는 규칙
    // 클라이언트가 1번 요청의 1번 응답, 즉) 요청이 없으면 응답 불가능
    // 서블릿 클래스 내 동일한 시그니처를 갖는 메소드는 존재 불가능
        // 즉) 하나의 서블릿에 doGet() 메소드 1개만 존재 가능 > 스프링 환경에서 보완 제공
}
