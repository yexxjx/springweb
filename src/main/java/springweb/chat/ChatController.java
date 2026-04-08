package springweb.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller // [1] @RestController 안 쓴 이유: HTTP 응답이 아니라서
public class ChatController {

    // @GetMapping HTTP 요청이 아니라서 XXXMapping 하지 않는다
    @MessageMapping("/chat") // 클라이언트가 서버에게 메시지를 보낸 주소 매핑, 클라이언트>서버
    // @ResponseBody HTTP 요청이 아니라서 하지 않는다.
    @SendTo("/topic/message") // 서버가 연동(구독)된 클라이언트들에게 메시지 응답 주소 매핑, 서버>클라이언트
    public Map<String, Object> sendmessage(Map<String, Object> message){
        System.out.println("message = " + message);
        System.out.println("ChatController.sendmessage");
        return message;
    }
}
