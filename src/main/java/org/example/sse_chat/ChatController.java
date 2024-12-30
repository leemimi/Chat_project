package org.example.sse_chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
public class MessageController {

    @GetMapping("/room")
    @ResponseBody
    public String room() {
        return "방입니다.";
    }

    @PostMapping("/messages")
    @ResponseBody
    public String postMessage() {
        return "안녕하세요 채팅 메시지입니다.";
    }


}
