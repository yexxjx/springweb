import { Outlet } from "react-router-dom";

export default function CommonLayout(props){
    return(<>
    <div>
        <header>
            Outlet 컴포넌트 알아보기
        </header>
        <article>
            <Outlet/> {/* 자식 컴포넌트가 랜더링 될 위치 */}
        </article>
        <footer>
            공통 레이아웃
        </footer>
    </div>
    </>);
}