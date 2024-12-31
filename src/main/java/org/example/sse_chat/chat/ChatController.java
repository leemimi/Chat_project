package org.example.sse_chat.chat;

import lombok.AllArgsConstructor;
import org.example.sse_chat.RsData;
import org.example.sse_chat.dto.ChatMessageResponse;
import org.example.sse_chat.dto.ChatWriteMessageResponse;
import org.example.sse_chat.dto.MessagesRequest;
import org.example.sse_chat.dto.WriteMessageRequest;
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

    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<ChatWriteMessageResponse> writeMessage(@RequestBody WriteMessageRequest writeMessage){

        ChatMessage cm = new ChatMessage(writeMessage.getAuthorName(), writeMessage.getContent());
        chatMessages.add(cm);

        return new RsData("200", "메시지가 작성되었습니다!", new ChatWriteMessageResponse(cm));
    }

    public record ChatMessageRequest(Long formId, Long toId) {
    }

    @GetMapping("/getMessages")
    @ResponseBody
    public RsData<ChatMessageResponse> getMessage(MessagesRequest messagesRequest){

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

        return new RsData<>("200", "ok", new ChatMessageResponse(messages, messages.size()));
    }
    @GetMapping("/room")
    public String room () {
        return "chat/room";
    }

}
