// p. 117
// 부모 컴포넌트로부터 상태변경함수를 props로 받아서
// props{ } 구조 분해 할당하여 a클릭하면
// 상태를 back으로 수정한다.


const BackComp=({setMode})=>{
    return(<>
    <li><a href="/" onClick={(event)=>{
        // event.preventDefault(); 이거 적으면 깜빡임 없음
        setMode('back'); // props 안쓰는 이유: 구조 분해해서
    }}>백엔드</a></li>
    <ul>
        <li>Java</li>
        <li>Oracle</li>
        <li>JSP</li>
        <li>Spring Boot</li>
    </ul>
    </>)
}
export default BackComp;