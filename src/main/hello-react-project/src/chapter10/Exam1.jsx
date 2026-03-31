import {useState} from 'react';
// 컴포넌트 생성: export default function 컴포넌트명(props){return jsx문법}
// 컴포넌트2
const TopComp=({MyData})=>{
    // const 상수, let 변수
    // (매개변수) => { } 화살표 함수, function(매개변수){ } 익명함수, function 함수명(매개변수){ } 선언적함수
    // {MyData}, 구조분해: 상위컴포넌트로부터 props객체를 그조화해서 변수로 받기(props객체 내 MyData 속성값 변수로 )
    // return: 함수의 반환값
    // 리엑트jsx return: jsx 반환
    // jsx란? HTML+JS 조합된 문법
    // JSX 주석? {/* */}
    // JAVA MAP, 배열/리스트.stram.map((반복변수)->{})
    // JS MAP, 배열/리스트.map((반복변수)=>{})
        // FOREACH 반복문(return X) VS MAP 반복문(return O)
        // JSX 문법에서 반복된 자료를 HTML로 구성하여 반환하는 구조 다수, MAP 활용 된다.
        // 예) MyData.front=['A','B','C']
        // <li key={i}>{A}</li> <li key={i}>{B}</li> <li key={i}>{C}</li>
    return(<>
    <ol>
        {/* 주석처리 */}
        <li>프론트엔드</li>
        <ul>
            {MyData.front.map((item, i)=><li key={i}>{item}</li>)}
        </ul>
        <li>백엔드</li>
        <ul>
            {MyData.back.map((item, i)=><li key={i}>{item}</li>)}
        </ul>
    </ol>
    </>)

}

// 컴포넌트1
export default function Exam1(props){
    const [MyData, setMyData]=useState({
        front:['HTML','CSS3', 'Javascript','jQuery'],
        back: ['JAVA', 'Oracle','JSP','Sprin Boot']
        });
    // 예) const [a,b]={a:1,b:2]}
    // a에는 1, b에는 2
    // useState 훅(hook: 갈고리-연결 상태/값/데이터<--갈고리-->컴포넌트)
        // 즉) 상태(state)가 (*주소/참조)값 변경되면 컴포넌트 랜더링 된다.
        // const[상태명, set상태명]=useState(초기값);
    // 리터럴: 키보드로부터 입력받은 상수(미리 만들어진) 자료, 주소/참조 값: 자료가 위치한 메모리 주소값
        // let a=3; 주소값 2개 [3(101번지), a(201번지)] 
        // let a=3; let b=3; 주소값 3개 [3(101번지), a(201번지), b(202번지)]
 
        // new는 새로 만드는 것
        // MemberDto dto1=new MemberDto(); 주소값 2개 [new MemberDto(301번지), dto1(401번지)]
        // MemberDto dto1=new MemberDto(); MemberDto dto2=new MemberDto(); 주소값 4개 [new MemberDto(301번지), new MemberDto(302번지), dto1(401번지),dto2(402번지)]
        // MemberDto dto3=dto2; 주소값 1개

    // 1) 랜더링 안되는 코드
    const addFront=()=>{
        MyData.front.push('React'); // MyData(101동 203호), push(): 배열 내 값 추가 함수, 주소 변경 없이 주소 값 추가
        setMyData(MyData);} // set상태명(새로운주소값); // 상태는 값 변경 감지하지 않고 상태의 주소값 변경 감지함(얕은 비교)

    // 2) 랜더링 되는 코드, 스프레드연산자{... 기존객체}, [...기존배열], {...기존객체, 속성명:값}, [...기존배열, 값]
    const addBack=()=>{
        const newBack=[...MyData.back, 'Node.js'];
        const newMyData={...MyData, back:newBack}; // newMyData= {...MyData(101동 203호)} (... 이 있으면 주소값 변경)
        setMyData(newMyData);                      // newMyData 105동 702호
    }
    return <>
    <h2> Chapter10 </h2>
    <TopComp MyData={MyData}/>
    <button type='button' onClick={addFront}>프론트 추가</button>
    <button type='button' onClick={addBack}>백엔드 추가</button>
    </>
}
// onclick: HTML 코드, onClick: JSX 코드(가상/가짜 DOM)
// 리액트가 가상/가짜 DOM 사용하는 이유: HTML이 객체 지향이 아니라서 HTML를 객체 지향으로 만든 구조 JSX
// DOM: document object model, HTML 마크업들을 객체 지향

