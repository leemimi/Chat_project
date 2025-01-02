package org.example.sse_chat.chat;

import lombok.AllArgsConstructor;
import org.example.sse_chat.RsData;
import org.example.sse_chat.dto.ChatMessageResponse;
import org.example.sse_chat.dto.ChatWriteMessageResponse;
import org.example.sse_chat.dto.MessagesRequest;
import org.example.sse_chat.dto.WriteMessageRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private List<ChatMessage> chatMessages = new ArrayList<>();
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<ChatWriteMessageResponse> writeMessage(@RequestBody WriteMessageRequest writeMessageRequest) {

        ChatMessage cm = new ChatMessage(writeMessageRequest.getAuthorName(), writeMessageRequest.getContent());
        chatMessages.add(cm);

        messagingTemplate.convertAndSend("/topic/chat/writeMessage", new ChatWriteMessageResponse(cm));

        return new RsData("200", "메세지가 작성되었습니다.", new ChatWriteMessageResponse(cm));
    }

    @GetMapping("/messages")
    @ResponseBody
    public RsData<ChatMessageResponse> messages(MessagesRequest messagesRequest) {
        List<ChatMessage> messages = chatMessages;
        if (messagesRequest.fromId() != null) {

            int index = IntStream.range(0, messages.size())
                    .filter(i -> chatMessages.get(i).getId() == messagesRequest.fromId())
                    .findFirst()
                    .orElse(-1);

            if (index != -1) {
                messages = messages.subList(index + 1, messages.size());
            }

        }

        return new RsData("200", "메세지 가져오기 성공", new ChatWriteMessageResponse(messages, chatMessages.size()));
    }

    @GetMapping("/room")
    public String room () {
        return "chat/room";
    }
}