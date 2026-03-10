// 컴포넌트 만드는 방법
// 1. 해당 폴더를 오른쪽 클릭 > [new file]
// 2. 첫 글자가 대문자로 시작하는 .jsx 파일 확장자로 생성
// 예) Exam1.jsx
// 3. function 컴포넌트명(){}
// 4. 컴포넌트 내 return (<>JSA문법</>)
// 5. 파일 내 내보내기 할 컴포넌트 1개, export default 컴포넌트명

function FrontComps(props){
    console.log(props);
    const liRows=[];
    for(let i=0; i<props.propData1.length; i++){
        liRows.push(
            <li key={i}>{props.propData1[i]}</li>
        );
    }
    return(<>
    <li>{props.frTitle}</li>
    <ul>
        {liRows}
    </ul>
    </>)
}

const BackComp=({propData2, baTitle})=>{
    const {a,b} = {a:1, b:2} // 구조 분해
    // 즉) 객체 내 값들을 각 변수로 분해, a속성 b속성으로 구성된 객체를 a변수 b변수로 분해한다
    console.log(a); // 구조 분해한 변수 사용
    // 즉) props는 객체인데 const(변수명1, 변수명2) = props
    const liRows=[];
    let keyCnt=0;
    for(let row of propData2){
        liRows.push(
            <li key={keyCnt++}>{row}</li>
        );
    }
    return(<>
    <li>{baTitle}</li>
    <ul>
        {liRows}
    </ul>
    </>)
}

// (1) 구조 분해 없이 여러 개 속성을 한꺼번에 받기
function MyComponent(props){
    // JSX 안에서는 주석 처리 불가능 
    return (<>
    <h2>props 객체 사용</h2>
    <p>
        {props.p1}, {props.p2},{props.p3},{props.p4}
    </p>
    </>)
}

// (2) 구조 분해 사용하여 필요한 속성만 받기
function MyComponent2({p1,p3}){
    return(<>
    <p> {p1}, {p3} </p>
    </>)
}

function Exam1(){
    const frontData=['HTML5', 'CSS3', 'Javascript', 'jQuery','React'];
    const backData=['Java', 'Oracle', 'JSP', 'Sprin Boot']
    return(<>
    <h3>프롭스 예제 p.87 React-props</h3>
    <ol>
        <FrontComps propData1={frontData} frTitle="프론트엔드"></FrontComps>
        <BackComp propData2={backData} baTitle="백엔드"/>
    </ol>
    <h3> props 객체 p.90 </h3>
    <MyComponent p1={'HTML5'} p2={'CSS3'} p3={'Javascript'} p4={'jQuery'} />
    <MyComponent2 p1={'HTML5'} p2={'CSS3'} p3={'Javascript'} p4={'jQuery'} />
    </>)
}
export default Exam1;