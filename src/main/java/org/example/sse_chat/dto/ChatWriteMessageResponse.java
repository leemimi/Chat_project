package org.example.sse_chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.sse_chat.chat.ChatMessage;

import java.util.List;

@AllArgsConstructor
@Getter
public class ChatWriteMessageResponse {
    private List<ChatMessage> chatMessages;
}
