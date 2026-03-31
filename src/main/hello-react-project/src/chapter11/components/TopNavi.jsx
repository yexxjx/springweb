import { Link, NavLink } from "react-router-dom";

export default function TopNavi(props){ // 상단메뉴/상단바=헤더 메뉴 컴포넌트
    // <a>: 클릭하면 브라우저 전체 새로고침 (깜빡임) <a href="URL">
    // <Link>: 클릭하면 브라우저 새로고침 없이 URL 변경 (깜빡임없음) <Link to="URL">
    return(
        <nav>
            <a href="/">Home</a>
            <NavLink to="/intro">인트로</NavLink>
            <NavLink to="/intro/router">Router관련Hook</NavLink>
            <Link to="/xyz">잘못된URL</Link>
        </nav>
    )
}