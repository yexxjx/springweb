import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom"
import axios from 'axios';

export default function View(props){

    // [1] 현재 URL 상의 쿼리스트링 값 가져오기, 조회할 게시물 번호 가져오기
    const [params] = useSearchParams();
    const bno=params.get("bno"); // URL 상의 bno값 가져오기

    // [3] axios 결과를 받는 상태 변수
    const [board, setBoard] =useState(null);

    // [2] axios 통신
    const findById=async()=>{
        try{
            const response=await axios.get(`http://localhost:8080/api/board/view?bno=${bno}`);
            const data=response.data;
            setBoard(data);
        } catch(e){console.log(e);}
    }

    useEffect(()=>{findById()},[]);
    // [5] 만약에 아직 axios의 결과가 없으면
    if(!board) return <div>불러오는 중</div>

    return(<div>
        <h2>게시물 상세</h2>
        <div>작성자: {board.mname}ㅣ작성일: {board.createDate.split("T")[0]}</div>
        <div>제목: {board.btitle}</div>

        { /* 만약에 웹에디터 사용할 경우에는 HTML 형식으로 지정되므로 HTML 형식으로 출력해야 함 */ }
        { /* 리액트는 가상dom이라서 직접적인 HTML 대입 차단한다. 직접 HTML 대입하는 방법 */ }
        <div dangerouslySetInnerHTML={{__html:board.bcontent}} />
        <div>
            { /* 만약에 첨부파일이 존재하면 업로드 경로에서 파일명의 다운로드 링크 추가  */
                board.bfile&&(
                    <>
                        <a href={`http://localhost:8080/upload/${board.bfile}`} download >
                            {board.bfile.split("_")[1]} 다운로드
                        </a>
                    </>
                )
            }
        </div>
    </div>)
}