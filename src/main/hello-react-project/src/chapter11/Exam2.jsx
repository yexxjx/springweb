import { Route, Routes } from "react-router-dom";
import Home from "./components/Home";
import TopNavi from "./components/TopNavi";
import NotFound from "./components/NotFound";
import CommonLayout from "./components/CommonLayout";
import LayoutIndex from "./components/LayoutIndex";
import RouterHooks from "./components/RouterHooks";

export default function Exam2(props){
    return(<>
        <TopNavi></TopNavi>
        <Routes> {/* 라우터들 설정 시작 */}
            <Route path='/' element={<Home/>} /> {/* 라우터 설정 */}

            {/* 중첩 라우터 */}
            <Route path='/intro' element={<CommonLayout/>}>
            {/* 경로가 별도로 없을 때 index 지정 */}
                <Route index element={<LayoutIndex/>} />
            {/* 쿼리스트링 갖는 URL */}
                <Route path='router' element={<RouterHooks/>} />
            </Route>
            <Route path='*' element={<NotFound/>} /> {/* 라우터 설정 */}
        </Routes>
    </>)
}