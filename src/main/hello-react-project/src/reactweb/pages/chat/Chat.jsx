import { useEffect, useRef, useState } from "react";
import axios from 'axios';

// [6] npm i @stomp/stompjs 설치
// [7] stomp 클라이언트 객체 import
import {Client} from '@stomp/stompjs'

export default function Chat(props){

    // [8] Client 객체를 저장하는 (래퍼런스)변수, useRef vs useState
    // 특정한 상태/값 저장하고 화면 렌더링에 영향을 주지 않는 저장소
    // useRef가 변해도 렌더링은 안된다, useState 변하면 렌더링 된다.
    // useRef={current:null}, useRef 객체 내 current 속성을 갖는다. current 저장된 상태/값

    // 예) 만약에 다른 상태들이 변해서 렌더링 했을 때 채팅 클라이언트는 변화가 없게끔하기 위해서
    const client=useRef(null); 

    // [13] 서버에게 받은 메시지들을 저장하는 상태 변수
    const [messages, setMessages] = useState([]);

    // [4] 로그인 정보 상태 변수
    const [loginUser, setLoginUser] = useState(null);

    // [3] AXIOS 회원정보 불러오기 함수
    const getMyInfo=async()=>{
        try{
            const response=await axios.get("http://localhost:8080/api/member3/my/info",
                {withCredentials:true})
                setLoginUser(response.data||null); // 단축평가: 조건&&참, 조건||거짓
                console.log(response.data);
        } catch(e){console.log(e);}
    }

    // [5] 해당 커포넌트 생명주기, 1번 실행
    useEffect(()=>{
        getMyInfo();

        // [9] 웹소켓 연결 객체 생성, new Client({brokerURl})
        const stomp=new Client({
            brokerURL: 'ws://localhost:8080/ws', // 스프링 웹소켓 엔드포인트
            reconnectDelay:5000, // 연동 실패시 5초마다 재연동
            onConnect:()=>{ // 연동 성공시 실행할 로직(함수)
                console.log("백엔드소켓과 클라이언트소켓 연결 성공");

                // [13] 메시지 구독(서버 > 클라이언트)
                // stomp.subscribe("구독주소", (받은메시지)=>{});
                stomp.subscribe("/topic/message", (메시지)=>{
                    console.log(메시지);
                    // 1) 서버에 받은 메시지를 JSON으로 변환, ***AXIOS는 자동, 소켓은 수동***
                        const data = JSON.parse(메시지.body); // JSON.parse(문자) 문자>JSON 변환
                        // JSON.stringify(객체); // JSON>문자
                    // 2) 서버에 받은 메시지를 상태 변수에 대입
                    messages.push(data);
                    setMessages([...messages]) // 얕은 비교 하기 때문에 [... 배열명], {...객체명}
                }); // 구독주소는 스프링의 소켓컨트롤러에서 설정한 주소 (@SendTo("/topic/message")
                
            }
        });

        // [10] 웹소켓 실행 .activate();
        stomp.activate();

        // [11] 웹소켓을 안전하게 useRef에 보관한다. useRef는 useState와 다르게 렌더링에 영향이 없다.
        client.current=stomp; // useRef의 값은 current 속성에 포함된다. 수정시 .current=새로운 값

        // [12] 컴포넌트 언마운트 될 때, 컴포넌트 생명주기(마운트=탄생/업데이트=인생/언마운트=죽음)
        return()=>{
            // 언마운트(화면에서 컴포넌트가 사라짐), 실행문;
            stomp.deactivate(); // 안전하게 소켓 닫기
        }

    },[])

    // [2] 입력받은 값을 저장하는 상태 변수
    const[sendMsg, setSendMsg]=useState('');

    // [1] 전송 버튼 클릭 시 입력받은 값 가져오기=서버에게 채팅 메시지 보내기
    const sendMessage=()=>{
        console.log('입력 받은 값: '+sendMsg); // 1) 입력받은 값 확인하기
        if(sendMsg==''){ // 2) 만약에 입력한 값이 없으면 메시지 전송 안함
            alert('메시지를 입력하세요.'); return;
        }

        // [15] 서버에 보낼 메시지 구성, sender 보낸사람, message 입력받은값
        const obj={sender:loginUser.mname, message:sendMsg}


        // [14] 연동된 소켓에 메시지 보내기, useRef의 상태값 호출, .current
        // stomp.publish({destination:"메시지를 받을 서버의 주소", body:내용물})
        client.current.publish({
            destination:"/app/chat", // 메시지를 받을 서버의 주소, 스프링 소켓컨트롤러(@MessageMapping)에서 설정한 주소 @MessageMapping("/chat")
            body: JSON.stringify(obj) // 보낼 메시지를 JSON 형식의 문자열 타입, AXIOS 자동
        }) // client.current==stomp(웹소켓) [11]에서 웹소켓 보관했기 때문에 current랑 stomp(웹소켓) 같다
        setSendMsg(''); // 메시지 전송 후 입력 상자 초기화
    }

    return(
        <div>
            <h2>채팅</h2>
            <div className="contents" style={{width:"300px"}}>
                {/* 받은 메시지들이 저장된 message 출력 */}
                {
                    messages.map((msg)=>{
                        return(
                            <div className="msgbox">
                                {/* 삼항연산자 이용한 html 분기 */}
                                {loginUser&&loginUser.mname==msg.sender?
                                (<>
                                    <div className="msg" style={{textAlign:"right"}}>{msg.message}</div>
                                </>):
                                (<>
                                    <div className="sender">{msg.sender}</div>
                                    <div className="msg">{msg.message}</div>
                                </>)
                                }
                            </div>
                        )
                    })
                }
                {/* 입력 상자 내 value 속성에 상태 변수 대입 시 입력이 불가능(입력은 되지만 렌더링은 안된 상태) 하다. */}
                {/* 상태 변수는 렌더링 해야 한다. 방안) onChange={(e) => {setXXXX(e.target)}} */}
                <input value={sendMsg} onChange={(e)=>{setSendMsg(e.target.value)}}/>
                <button onClick={sendMessage}>전송</button>
            </div>


        </div>
    )
}