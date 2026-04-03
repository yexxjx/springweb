import { Route, Routes } from "react-router-dom";
import Login from "./pages/member/Login"
import Header from "./components/Header";
import Write from "./pages/board/Write";

export default function App(props){
    return(
    <div id="wrap">
        <Header /> {/* 헤더 */}
        <Routes>
            {/* 본문들 */}
            <Route path="/member/login" element={<Login/>} />
            <Route path="/board/write" element={<Write/>} />
        </Routes>
        {/* 푸터 */}
    </div>
    )
}