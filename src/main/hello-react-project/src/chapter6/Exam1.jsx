// 컴포넌트 만드는 방법
// 1. 해당 폴더를 오른쪽 클릭 > [new file]
// 2. 첫 글자가 대문자로 시작하는 .jsx 파일 확장자로 생성
// 예) Exam1.jsx
// 3. function 컴포넌트명(){}
// 4. 컴포넌트 내 return (<>JSA문법</>)
// 5. 파일 내 내보내기 할 컴포넌트 1개, export default 컴포넌트명

// p.111 컴포넌트 가져오기? 다른 jsx 파일에서 컴포넌트
import BackComp from "./BackComp";
import FrontComp from "./FrontComp";


export default function Exam1(props){
    return (<>
    <h3> Chapter6 p.110</h3>
    <ol>
        <FrontComp></FrontComp>
        <BackComp onMyEvent2={(msg)=>{alert(msg)}}></BackComp>
    </ol>
    </>)
}