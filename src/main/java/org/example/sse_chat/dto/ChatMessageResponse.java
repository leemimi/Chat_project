package org.example.sse_chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sse_chat.chat.ChatMessage;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChatMessageResponse {

    private List<ChatMessage> chatMessages;
    private int toalCount;

}
