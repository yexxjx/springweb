import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';

export default function Create(props){

    // [*] location.href의 깜빡거림 없이 router로 페이지 전환, navigate("이동할경로")
    const navigate=useNavigate();

    // [1] 등록함수, 등록하기 버튼을 클릭하면
    const taskCreate=async(event)=>{
        event.preventDefault(); // <a> <form> 이동 이벤트 제거
        // event란? 해당 이벤트를 발생시킨 정보를 담고 있는 객체

        // [2] 입력받은 값 가져오기, document.querySelector() vs
        // form내 식별자는 name 속성 사용
        let title=event.target.title.value;
        let content=event.target.content.value;
        let requester=event.target.requester.value;
        let status=event.target.status.value;

    // [2] (REST API로 전송할) 객체 생성
    let obj={title, content, requester, status} // console.log(obj);

    // [3] AXIOS 사용
    const response=await axios.post('http://localhost:8080/api/task', obj);
    const data=response.data;

    // [4] 결과에 따른 분기
    console.log(data);
    if(data.id>=1){
        alert('등록 성공'); 
        navigate("/");} // location.href="/"
    else{alert('등록 실패');}
    }

    return(<>
    <h2> 등록 페이지 </h2>
    <Link to='/'> 뒤로가기 </Link>
    <form onSubmit={taskCreate}>
        제목: <input name="title" /> <br/>
        내용: <textarea name="content"></textarea> <br/>
        요청자명: <input name="requester" /> <br/>
        상태: <select name="status">
            <option>요청</option>
            <option>진행중</option>
        </select> <br/>
        <button type="submit">등록하기</button>
    </form>
    </>)
}