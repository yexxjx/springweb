import { createRoot } from 'react-dom/client'
import './index.css'
// import App from './App.jsx'

// ** index.html에서 root 가져오기 **
const root=document.querySelector('#root');

// chapter12 예제
import App from './chapter12/App.jsx';
import { BrowserRouter } from 'react-router-dom';
createRoot(root).render(
    <BrowserRouter>
        <App/>
    </BrowserRouter>
)

// chapter11 예제
// import Exam2 from './chapter11/Exam2.jsx';
// import {BrowserRouter} from "react-router-dom";

// createRoot(root).render(
//     <BrowserRouter>
//         <Exam2/>
//     </BrowserRouter>
// ); 

// chapter10 예제
// import Exam1 from './chapter10/Exam1.jsx';
// createRoot(root).render(<Exam1/>)

// chapter9 예제
// import Exam2 from './chapter9/Exam2.jsx';
// createRoot(root).render(<Exam2/>)

// chapter8 예제
// import Exam1 from './chapter8/Exam1.jsx';
// createRoot(root).render(<Exam1/>)

// chapter7 예제
// import Exam2 from './chapter7/Exam2.jsx';
// createRoot(root).render(<Exam2/>)

// chapter6 예제
// import Exam1 from './chapter6/Exam1.jsx'
// createRoot(root).render(<Exam1/>)

// chapter5 예제
// import Exam2 from './chapter5/Exam2.jsx'
// createRoot(root).render(<Exam2/>);

// chapter4 예제
// import Exam1 from './chapter4/Exam1.jsx' // 컴포넌트 불러오기
// createRoot(root).render(< Exam1 />);

// 기존코드
// createRoot(document.getElementById('root')).render(<App />)

/*
[1] index.html(싱글페이지)에서 root라는 id갖는 div 호출
const root= document.querySelector('#root');

[2] root 마크업에 랜더링(render)
createRoot(root).render(<h1> 안녕하세요! </h1>) VS root.innerHTML="<h1> 안녕하세요! </h1>";
*/