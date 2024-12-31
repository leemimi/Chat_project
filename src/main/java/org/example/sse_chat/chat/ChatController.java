package org.example.sse_chat.chat;

import org.example.sse_chat.RsData;
import org.example.sse_chat.dto.ChatMessageResponse;
import org.example.sse_chat.dto.ChatWriteMessageResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private List<ChatMessage> chatMessages = new ArrayList<>();

    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<ChatWriteMessageResponse> writeMessage() {

        ChatMessage message = new ChatMessage("김철수", "안녕하세요, 채팅보냅니다~");
        chatMessages.add(message);

        message = new ChatMessage("안영희", "안녕하세요,반갑습니다~");
        chatMessages.add(message);

        return new RsData("200", "ok", new ChatWriteMessageResponse(chatMessages));
    }

    @GetMapping("/getMessages")
    @ResponseBody
    public RsData<ChatMessageResponse> getMessage(){


        return new RsData("200", "ok", new ChatMessageResponse(chatMessages));
    }

}
