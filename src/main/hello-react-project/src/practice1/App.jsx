import { Route, Routes } from "react-router-dom";
import Index from "./components";
import Create from "./components/create";
import Detail from "./components/detail";
import Edit from "./components/edit";

export default function App(props){
    return(<>
    컴포넌트
    <Routes>
        <Route path="/" element={<Index />} />
        <Route path="/task/create" element={<Create />} />
        <Route path="/task/detail" element={<Detail />} />
        <Route path="/task/edit" element={<Edit />} />
    </Routes>
    </>)
}