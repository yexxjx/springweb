import { useEffect, useState } from "react";
import axios from 'axios';

const GlobalTop=(props)=>{
    console.log("[1] 컴포넌트 실행");
    const[myList, setMyList]=useState([]);

    // [1] AXIOS 사용하기, 설치: npm i axios, import: import axios from 'axios'
    // async function axiox1(){} 이것도 사용 가능
    const axios1=async()=>{ // axios 통신하는 함수 하나 만들기, async 동기화 한다.
        const response=await axios.get('./json/myData.json'); // await 동기화된 axios 통신한다.
        // response에는 HTTP 응답 과년 정보들이 객체로 들어있다.
        // 그중에 response.data는 실질적인 결과물(내용물/body) 들어있다.
        const result=response.data; // 통신 결과 내 .data가 실질적인 결과물(내용물/body) 가져온다.
        setMyList(result); // axios 통신 결과를 상태변수에 대입한다. <렌더링>
    }

    // [2]
    // 1) useEffect(()=>{}) 최초 실행, 렌더링할때마다실행
    // 2) useEffectt(()=>{},[]) 최초실행
    // 3) useEffect(()=>{},[상태변수1, 상태변수2]) 최초실행, 특정상태변경될때마다실행
    useEffect(()=>{
        console.log('[3] useEffect 실행');
        axios1();
    },[ ])

    // [3] 현재 상태(myList=>json=>axios) 정보를 반복하여 html 구성 함수
    // 리스트/배열변수명.map((반복변수)=>{return(<> JSX </>)}) // 주로 HTMl 구성할 때 사용
    let listTag=myList.map((data)=>{
    // 첫번째 반복 data={"num":1, "id":"yu", "name":"유비", "cell":"(02) 235-1111"},
    // 두번째 반복 data={"num":2, "id":"kwan", "name":"관우", "cell":"(051) 235-2222"},
    // 세번째 반복 data={"num":3, "id":"jang", "name":"장비", "cell":"(031) 235-3333"}

    // onClick={함수선언또는함수명}
    return(
    <li key={data.id}>
        <a href={data.id} data-id={data.num} onClick={(e)=>{e.preventDefault(); props.myListClick(e.target.dataset.id);}}>{data.id}</a>
    </li>
    )
    });
    // 변수 예측 listTag=<li><a>yu</a></li>, <li><a>kwan</a></li>, <li><a>jang</a></li>


    console.log('[2] return 실행')
    return(<>
        <nav>
            <ul>
                {listTag}
                {/* VS <li><a>{data.id}</a></li> */}
            </ul>
        </nav>
    
    </>)
}

// 클릭된 정보를 그리는 컴포넌트
const ContentBody=(props)=>{
    return(<>
        <div>
            <h2> {props.myResult.name} </h2>
            <ul>
                <li> num: {props.myResult.num} </li>
                <li> id: {props.myResult.id} </li>
                <li> cell: {props.myResult.cell} </li>
                <li> description: {props.myResult.description} </li>
            </ul>
        </div>
    </>)
}

// LocalJsonFetcher.jsx
export default function LocalJsonFetcher(props){

    const[myResult, setMyResult]=useState({}); // 상태변수, 배열 아닌 객체, 빈 객체
    console.log(myResult);

    return(<>
    <h2>내부 서버 통신</h2>
    <GlobalTop myListClick={async (num)=>{ // async ()=>{} vs async function(){} vs async function함수명(){}
    console.log('클릭',num);

    // fetch 대신에 axios
    const response=await axios.get(`./json/dto${num}.json`);
    const result=response.data;
    setMyResult(result);
    }}> </GlobalTop>
    <ContentBody myResult={myResult}></ContentBody>
    </>)

}