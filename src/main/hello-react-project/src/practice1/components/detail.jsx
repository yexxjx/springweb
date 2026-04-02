import { Link, useSearchParams } from "react-router-dom"
import axios from 'axios';
import { useEffect, useState } from "react";

export default function Detail(props){

    // [1] 쿼리스트링의 id 가져오기
    const[searchParams, setSearchParams]=useSearchParams();
    const id=searchParams.get("id");

    // [3] REST API에게 받은 자료를 저장하는 상태(state)변수
    const [task, setTask]=useState(null); // 초기값 빈 배열

    // [2] REST API 이용하여 개별 조회
    const taskDetail=async()=>{
            const response=await axios.get('http://localhost:8080/api/task/detail?id='+id);
            const data=response.data;
            setTask(data);
    }

    // [4] 최초 1번 REST API 요청
    useEffect(()=>{taskDetail()}, []);

    // [5] 만약에 최초 렌더리이면
    if(task==null) return <div>가져오는 중..</div>
    return(<>
    <h2> 상세 페이지 </h2>
    <Link to="/">뒤로가기</Link>
    <p>번호: {task.id}</p>
    <p>제목: {task.title}</p>
    <p>내용: {task.content}</p>
    <p>요청자명: {task.requester}</p>
    <p>상태: {task.status}</p>
    <p>등록일: {task.updateDate.split('T')[0]}</p>

    </>)
}