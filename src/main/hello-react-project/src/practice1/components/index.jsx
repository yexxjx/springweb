import { useEffect, useState } from "react";
import axios from 'axios';
import { Link } from "react-router-dom";

export default function Index(props){

    // [2] REST API에게 받은 자료를 저장하는 상태(state)변수
    const [taskAry, setTaskAry] = useState([]); // 초기값 빈 배열, 추후에 rest 결과 담기

    // [1] REST API에게 전체조회
    const taskRead=async()=>{
        try{
            const response=await axios.get('http://localhost:8080/api/task');
            const data=response.data;
            setTaskAry(data);
        }catch{console.log(e)}
    }

    // [3] 컴포넌트 생명주기, REST API 통신 응답 처리된 후 재렌더링(새로고침)
    useEffect(()=>{taskRead();}, []); // 의존성 배열이 빈 배열이면 최초 1번 실행

    // [4] 삭제 요청 REST API, delete update write가 존재하는 키워드 유의
    const taskDelete=async(id)=>{
        const result=confirm('정말 취소할까요?'); // 확인true 취소false
        if(result==true){
            const response=await axios.delete('http://localhost:8080/api/task?id='+id);
            console.log(response);
            // 본문이 없으므로 본문으로 분기하지 않고 HTTP 응답 코드로 분기
            if(response.status==204){
                alert('삭제 성공');
                await taskRead();}
            else{alert('삭제 실패');}
        }
    }

    return(<>
    <h2> 전체 조회 페이지 </h2>
    <a href="/task/create"> 등록 </a> {/* HTML 이동 마크업 */}
    {/* <Link to="/task/create"> 등록 </Link> react router 방식 */}
    <table>
        <thead border="1">
            <tr>
                <th>번호</th><th>제목</th><th>요청자명</th>
                <th>상태</th><th>등록일</th><th>비고</th>
            </tr>
        </thead>
        <tbody>
            {
                taskAry.map((task)=>{
                    return (<>
                    <tr>
                        <td>{task.id}</td><td>{task.title}</td><td>{task.requester}</td>
                        <td>{task.status}</td><td>{task.updateDate.split('T')[0]}</td>
                        <td>
                            <button><Link to={'/task/detail?id='+task.id}>상세보기</Link></button>
                            <button>수정</button>
                            <button onClick={()=>{taskDelete(task.id);}}>삭제</button>
                        </td>
                    </tr>
                </>)
            })
        }  
        </tbody>
    </table>
    </>)
}