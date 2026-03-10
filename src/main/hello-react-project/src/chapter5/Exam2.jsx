function FrontComp(props){
    return(<>
    {/* JSX(return 안에서 사용되는 문법)에서 주석처리하는 방법 */}
    <li><a href='/' onClick={()=>{ props.onMyEvent1();}}>프론트엔드</a></li>
    <ul>
        <li>HTML5</li> <li>CSS3</li> <li>Javascript</li> <li>jQuery</li>
    </ul>
    </>)
}

function Exam2(){
    function 출력함수(){alert('출력된메시지');}
    // 익명함수? 이름이 없는 함수(재사용이 안된다/일회성 또는 이벤트 함수)
    // 화살표함수? 이름이 없고 => 화살표 표현식 사용하는 함수 (주로 변수에 저장하여 재사용한다.)
    return (<>
    <h3> 이벤트 처리 p.100 </h3>
    <button onClick={출력함수}>리액트버튼</button>
    <button onClick={function(){alert('출력된메시지2');}}>리액트버전</button>
    <button onClick={()=>{alert('출력되는메시지3');}}>리액트버튼2</button>
    <FrontComp onMyEvent1={()=>{
        alert('프론트엔드 클릭됨(부모전달)');
    }}></FrontComp>
    </>)
}
export default Exam2;

/*
리액트 이벤트에서 주의할 점
1. onClick 카멜표기법 합성이벤트(리액트가 핸들러/연결 통해 이벤트 실행)
2. onClick에서 함수 실행하는 구조 아니고 전달하는 구조
    1) 선언, function 함수명(){}
    2) 호출, 함수명()
    3) 함수란? 여러 개 계산/코드(데이터x) 묶음
    *** HTML: <button onClick="출력함수()">
    *** REACT: <button onClick={출력함수}>, <button onClick={()=>{alert('');}}>
*/