console.log("detail.js");

// 1. 쿼리스트링이란? URL(주소) 뒤에 ?로 매개변수(값) 포함하는 경우
// 2. JS에서 쿼리스트링의 갑 가져오는 방법
// new URLSearchParams(location.search).get("변수명");
const bno=new URLSearchParams(location.search).get("bno");
console.log(bno);

// 개별 조회 함수 정의
const 개별조회= async()=>{
    // 1) 어디에
    const boardBox=document.querySelector('#boardBox');
    // 2) 무엇을, 쿼리스트링에 존재하는 (클릭한 게시물) bno에 게시물 정보
    const response=await axios.get(`/board/detail?bno=${bno}`);
    const data=response.data;
    let html=`제목 : <div> ${data.btitle} </div>
             작성자 : <div> ${data.bwriter} / ${data.createDate} </div>
             내용 : <div> ${data.bcontent} </div>
             <button onclick="개별수정( ${data.bno})"> 수정 </button>
             <button onclick="개별삭제( ${data.bno})"> 삭제 </button>`; // +=누적 =대입
    // 3) 출력
    boardBox.innerHTML=html;
}
// 개별 조회 함수 호출
개별조회();

// 개별 삭제 함수 정의
const 개별삭제=async(bno)=>{
    // 1) 현재 게시물을 삭제하기 위해 현재 게시물 번호 확인(bno는 매개변수 또는 쿼리스트링)
    // 2) axios 이용해서 서버에게 게시물 삭제 요청 결과 받기
    const response=await axios.delete(`/board?bno=${bno}`);
    const data=response.data;
    // 3) 결과
    if(data==true){
        alert("삭제 성공");
        location.href="/종합예제10/index.html";
    } else{alert("삭제 실패")}
}


// 개별 수정 함수 호출
const 개별수정=async(bno)=>{
    // 1) 현재 게시물 수정하기 위해 현재 게시물 번호 확인(bno)
    console.log(bno);
    // 2) 수정할 내용 입력받기, 테스트용
    const btitle = prompt("수정할 제목");
    const bcontent = prompt("수정할 내용");
    const bwriter = prompt("수정할 작성자");
    // 3) axios 이용한 서버에게 수정 요청 응답 받기
    const obj={bno, btitle, bcontent, bwriter};
    const response=await axios.put(`/board`, obj);
    const data=response.data;
    // 4) 결과
    if(data==true){
        alert("수정 성공");
        location.reload();  // reload 현재 페이지를 새로고침함
    } else{alert("수정 실패");}
}

// ===== comment =====

const 댓글조회=async()=>{
    const commentTable=document.querySelector('#commentTable');
    let html=``;
    const response=await axios.get("/comment");
    const data=response.data;
    for(index=0;index<=data.length-1;index++){
        const commentDto=data[index];
        html+= `<div>
                    <div>
                        <span>${commentDto.cwriter}</span>
                        <span>${commentDto.createDate}</span>
                        <span>
                            <button onclick="댓글수정(${commentDto.cno})">수정</button>
                            <button onclick="댓글삭제(${commentDto.cno})">삭제</button>
                        </span>
                    </div>
                    <div> ${commentDto.ccontent} </div>
                </div>`
    } commentTable.innerHTML=html;  
}
댓글조회();

const 댓글등록=async()=>{
    const cwriterInput=document.querySelector('.cwriterInput');
    const ccontentInput=document.querySelector('.ccontentInput');

    const cwriter=cwriterInput.value;
    const ccontent=ccontentInput.value;
    
    const obj={cwriter,ccontent};
    const response=await axios.post("/comment", obj);
    const data=response.data;
    if(data==true){
        alert("댓글 등록 성공");
        location.reload();
    } else{alert("댓글 등록 실패");}
}

const 댓글수정=async(cno)=>{
    const cwriter=prompt("수정할 작성자");
    const ccontent=prompt("수정할 내용");
    const obj={cno, cwriter, ccontent};
    const response=await axios.put(`/comment`,obj);
    const data=response.data;
    if(data==true){
        alert("댓글 수정 성공"); 
        location.reload();
    } else{(alert("댓글 수정 실패"))}
}

const 댓글삭제=async(cno)=>{
    const response=await axios.delete(`/comment?cno=${cno}`);
    const data=response.data;
    console.log(data);
    if(data==true){
        alert("댓글 삭제 성공");
        location.reload();
    } else{alert("댓글 삭제 실패")}
}