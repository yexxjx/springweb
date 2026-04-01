import { NavLink } from "react-router-dom";

export default function TopNavi(props){
    // jsx와 js 구분: 컴포넌트(함수)내 return(반환값) 뒤로 jsx 문법 그 외 js
    // jsx에서 html에 없는 마크업들은 모두 컴포넌트이며 외부 호출시 import 한다.
    // a마크업은 새로고침 포함/ NavLink는 새로고침 미포함
    return(<>
    <nav>
        <a href="/">생명주기</a> 
        <NavLink to="/">생명주기</NavLink>
        <NavLink to="/local">내부통신</NavLink>
        <NavLink to="/external">외부통신</NavLink>
    </nav>
    </>)
}