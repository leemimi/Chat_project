package org.example.sse_chat.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ChatMessage {
    private Long id;
    private LocalDateTime createDate;
    private String authorName;
    private String content;
    public ChatMessage( String authorName, String content) {
        this.id = GenerateId.getId();
        this.authorName = authorName;
        this.content = content;
        this.createDate = LocalDateTime.now();
    }

    static class GenerateId {
        private static long id = 0;
        public static long getId() {
            return id++;
        }
    }
}