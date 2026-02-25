package example.day02.practice1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/practice1") // 해당 서블릿 클래스의 주소는 "/practice1" 정의하기
public class Practice1 extends HttpServlet {
// 3. 쿼리스트링 방식으로 데이터와 함께 서블릿 주소를 요청 보내기   EX) 서블릿주소?value=10


// 1. Post 메소드 일때  : 보내온값의  * 2 를 해서 결과값을 응답하기  EX) 20
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value = req.getParameter("value");
        int value2 = Integer.parseInt( value );
        resp.getWriter().println( value2 * 2 ); // 결과값은 메모장 결과 누르면 나옴
        System.out.println("value2 = " + value2+2);

    }

// 2. GET 메소드 일때  : 보내온값의  + 2 를 해서 결과값을 응답하기 EX) 12
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int value = Integer.parseInt( req.getParameter("value") ) ; // int a = ( (3+3) *2 )
    resp.getWriter().println( value + 2 );
}

// 3. Put 메소드 일때  : 보내온값의  / 2 를 해서 결과값을 응답하기 EX) 5
    @Override protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int value = Integer.parseInt( req.getParameter("value") ) ;
    resp.getWriter().println( value / 2 );
    }

// 4. Delete 메소드 일때  : 보내온값의  % 2 를 해서 결과값을 응답하기 EX) 0
@Override protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int value = Integer.parseInt( req.getParameter("value") ) ;
    resp.getWriter().println( value % 2 );
}
}
