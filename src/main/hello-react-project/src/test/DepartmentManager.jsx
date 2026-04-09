import axios from 'axios';
import { useEffect, useState } from 'react';

export default function DepartmentManager() {

  // 부서 등록
  const departcreate=async(e)=>{
    e.preventDefault();
    try{
    const dname=e.target.dname.value;
    const obj={dname:dname}
    const response=await axios.post("http://localhost:8080/depart", obj);
    if(response.data){
      alert('부서 등록 성공');
      location.href="/";
    } else {alert('부서 등록 실패');}}
    catch{alert('중복된 부서명 등록으로 실패');}
  }

  // 부서 조회
  const [departList, setDepartList] =useState([]);
  const departread=async()=>{
      const response=await axios.get("http://localhost:8080/depart");
      const data=response.data;
      setDepartList(data);
  }
  useEffect(()=>{departread();},[])

  // 부서 수정
  const departupdate=async(dno)=>{
    const newdepart=prompt("수정할 부서명 입력");
    const obj={dname:newdepart}
    const response=await axios.put(`http://localhost:8080/depart/update?dno=${dno}`, obj);
    console.log(newdepart);
    if(response.data){
      alert('부서 수정 성공');
      location.href="/";
    } else{alert('부서 수정 실패');}
  }

  // 부서 삭제
  const departdelete=async(dno)=>{
    const response=await axios.delete(`http://localhost:8080/depart?dno=${dno}`);
    if(response.data==true){
      alert('부서 삭제 성공');
      location.href="/";
    } else {alert('부서 삭제 실패');}
  }

  return (<>
    <div className="sidebar">
      <h3>부서 관리</h3>

      <form className="dept-input" onSubmit={departcreate}>
        <input name="dname" type="text" placeholder="신규 부서명 입력" />
        <button>추가</button>
      </form>

      <table className="dept-table">
        <thead>
          <tr>
            <th>부서명</th>
            <th>관리</th>
          </tr>
        </thead>
        <tbody>
          {departList.map(depart=>{
            return(
               <tr key={depart.dno}>
                <td>{depart.dname}</td>
                <td>
                  <span onClick={()=>departupdate(depart.dno)} className="edit">수정</span>
                  <span onClick={()=>departdelete(depart.dno)} className="delete">삭제</span>
                </td>
              </tr>

            )
          })
          }
         
        </tbody>
      </table>
    </div>
  </>);
}