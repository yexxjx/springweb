import axios from 'axios';
import { useEffect, useState } from 'react';

export default function DepartmentManager() {

  const departcreate=async(e)=>{
    e.preventDefault();
    const dname=e.target.dname.value;
    const obj={dname:dname}
    const response=await axios.post("http://localhost:8080/depart", obj);
    if(response.data){
      alert('부서 등록 성공');
      location.href="/";
    } else {alert('부서 등록 실패');}
  }

  const [departList, setDepartList] =useState([]);
  const findAll=async()=>{
      const response=await axios.get("http://localhost:8080/depart");
      const data=response.data;
      setDepartList(data);
  }
  useEffect(()=>{findAll();},[])

  const departdelete=async(dno)=>{
    const response=await axios.delete(`http://localhost:8080/depart?dno=${dno}`);
    if(response.data==true){
      alert('부서 삭제 성공');
      location.href="/";
    } else {alert('부서 삭제 실패');}
  }

  // const departupdate=async()=>{
  //   const response=await axios.put(`http://localhost:8080/depart?dno=${dno}`);
  // }

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
                  <span className="edit">수정</span>
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