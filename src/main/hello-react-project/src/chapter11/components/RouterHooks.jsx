import { useLocation, useSearchParams } from "react-router-dom";

export default function RouterHooks(props){

    const loation=useLocation(); // [1] useLocation 훅 선언한다. 현재 URL 정보 가져오기

    // [1] useSearchParams 훅 선언한다.
    const[searchParams, setSearchParams] = useSearchParams();
    const mode=searchParams.get('mode'); // 쿼리스트링 내 mode변수명 값 가져오기
    const pageNum=searchParams.get('pageNum'); // 쿼리스트링 내 pageNum변수명 값 가져오기

    // [2] changeMode, 만약에 mode가 lsit이면 view 변경 아니면 list 변경
    const chandeMode=()=>{
        const nextMode=(mode=='list')?'view':'list';
        setSearchParams({mode:nextMode, pageNum:pageNum}); // 속성명이랑 데이터값이 같으면 생략해도 됨
    }

    // [3] nextPage, 만약에 pageNum가 null이면 1페이지 아니면 +1
    const nextPage=()=>{
        let pageTemp=(pagenum==null||isNaN(pageNum))?1:parseInt(pageNum)+1; 
        setSearchParams({mode:mode, pageNum:pageTemp});
    }

    // [4] prevPage, 만약에 pageNum가 null이면 1페이지 아니면 -1
    const prevPage=()=>{
        let pageTemp=(pagenum==null||isNaN(pageNum))?1:parseInt(pageNum)-1;
        setSearchParams({mode:mode, pageNum:pageTemp});
    }


    return(<>
    <h2> 라우터 관련 HOOK </h2>
    <div>
        <ul>
            <li> URL:{location.pathname}</li>
            <li> 쿼리스트링: {location.search}</li>
            <li> mode: {mode} </li>
            <li> pageNum: {pageNum} </li>
        </ul>
        <button onClick={changeMode}>Mode 변경</button>
        <button onClick={prevPage}>이전Page</button>
        <button onClick={nextPage}>다음Page</button>
    </div>
    </>);
}