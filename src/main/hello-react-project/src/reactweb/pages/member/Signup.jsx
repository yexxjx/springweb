import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function Signup(props){
    const navigate=useNavigate();
    const membersignup=async(e)=>{
        e.preventDefault();

        const mid=e.target.mid.value;
        const mpwd=e.target.mpwd.value;
        const mname=e.target.mname.value;

        const obj={mid:mid, mpwd:mpwd, mname:mname}

        const response=await axios.post("http://localhost:8080/api/member/signup",obj);
        const data=response.data;
        
        if(data==true){
            alert("회원가입 성공");
            navigate("/signup");
        } else{alert("회원가입 실패");}
    }
return(<>
<div>
    <h2>회원가입 페이지</h2>
    <form onSubmit={membersignup}>
        아이디: <input name="mid"/> <br/>
        비밀번호: <input name="mpwd" /> <br/>
        이름: <input name="mname" />
        <button type="submit">등록하기</button>
    </form>
</div>


</>)
}