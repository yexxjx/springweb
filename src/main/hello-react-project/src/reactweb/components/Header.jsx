import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from 'axios';

export default function Header(props){
    // [2] 로그인 상태 저장하는 상태 변수,
    const[login,setLogin]=useState(false); // 초기값은 false 로그인 안했다는 뜻
    // [3] 로그인 중인 회원 정보 담는 상태 변수
    const[user,setUser]=useState(null); // 초기값은 비로그인 상태

    // [1] 로그인 상태에 따라 상단 메뉴 분기
    const getMyInfo=async()=>{
        // 1) 로그인시 localStorage 저장한 token 가져오기, .setItem, .getItem
        // const token=localStorage.getItem('token'); // cookie 사용하므로 주석
        // 2) 만약에 토큰이 없으면 함수 종료, 로그인 상태
        // if(!token){setLogin(false); return;} // cookie 사용하므로 주석
        // 3) 헤더에 표시할 로그인 된 유저 아이디 가져오기
        const response=await axios.get(
            'http://localhost:8080/api/member3/my/info', // 통신할(스프링 컨트롤러 매핑) 주소
            {withCredentials:true} // header에 토큰 전송이 아닌 쿠키(+토큰) 전송으로 변경
            // {headers:{Authorization:`Bearer ${token}`}} // {headers:{속성명:값}} // cookie 사용하므로 주석
            // AXIOS 특징: Content-Typ: application/json 기본값
            // 만약에 Content-Type이 json이 아닌 경우 명시해야한다.
    );
        // 4) 통신 결과 분기
        const data=response.data;
        if(data||data!=false){ // 응답 자료가 존재하면
            setLogin(true); // 로그인 상태 변경
            setUser(data); // 응답받은 자료(회원정보) 저장
        } else{
            setLogin(false); // 비로그인상태 변경
        }
    }
    // [4] 헤더가 열리면 최초 1번 실행, 로그인 상태(백엔드 검증)
    useEffect(()=>{getMyInfo();}, [ ])

    // [5] 로그아웃 // cookie 버전
    const logout= async()=>{
        // 1) axios
        const response=await axios.get('http://localhost:8080/api/member3/logout', // 통신할 서버의 경로(controller 매핑 주소)
            {withCredentials:true} // 쿠키(+토큰) 전송
        ); 
        // 2) 로그인 상태 변경, 안내 후 페이지 변경
        setLogin(false);
        alert('로그아웃'); 
        location.href="/";

        // token 버전
        // 1) localStorage에서 token 제거, .removeItem()
        // localStorage.removeItem('token');
        // 2) 로그인 상태 변경
        // setLogin(false);
        // 3)
        // alert('로그아웃'); location.href="/";
    }
    // JS 삼항연산자, 조건 ? 참실행문 : 거짓실행문, 조건의 참이면 참실행문 거짓이면 거짓실행문
    // JS 단축평가, 조건 && 실행문, 조건이 참이면 실행문 거짓이면 생략
    return(
        <div>
            {/* 로그인 상태에 따른 메뉴 분기 */}
            <Link to="/">홈</Link>ㅣ
            <Link to="/board">게시물</Link>ㅣ

            {/* 비로그인메뉴 */}
            {login==false&&(<>
            <Link to="/member/login">로그인</Link>ㅣ
            <Link to="/member/signup">회원가입</Link>ㅣ
            </>)} 

            {/* 로그인메뉴 */}
            {login==true&&(<>
            <span>{user.mid}님</span>ㅣ
            <Link to="/member/page">내정보</Link>ㅣ
            <Link to="/board/write">글쓰기</Link>ㅣ
            <Link to="/chat">채팅방</Link>ㅣ
            <button onClick={logout}>로그아웃</button>
            </>)} 
            <hr/>
        </div>
    )
}